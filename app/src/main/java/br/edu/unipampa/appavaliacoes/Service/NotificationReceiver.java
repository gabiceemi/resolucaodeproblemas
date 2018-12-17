package br.edu.unipampa.appavaliacoes.Service;

import android.app.NotificationManager;
import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;

import java.util.List;
import java.util.concurrent.TimeUnit;

import br.edu.unipampa.appavaliacoes.Controller.NotificationController;
import br.edu.unipampa.appavaliacoes.Model.Notificacao;
import br.edu.unipampa.appavaliacoes.R;

public class NotificationReceiver extends JobService {

	// folga para executar as tarefas, antes do horário da notificação
	long TEMPO_FOLGA = TimeUnit.MINUTES.toMillis(2);

	@Override
	public boolean onStartJob(JobParameters params) {
		new JobTask(this).execute(params);
		return true;
	}


	@Override
	public boolean onStopJob(JobParameters params) {
		return true;
	}

	/**
	 * Agenda o job apenas se o horário for novo for antes do antigo job
	 *
	 * @param when
	 */
	private void agendarJob(long when, Context context) {
		when -= TEMPO_FOLGA;
		ComponentName serviceComponent = new ComponentName(context.getPackageName(), NotificationReceiver.class.getName());
		JobInfo.Builder builder = new JobInfo.Builder(0, serviceComponent);
		builder.setOverrideDeadline(when);
		builder.setPersisted(true); // persistir o trabalho durante a inicialização
		// builder.setMinimumLatency(TimeUnit.MINUTES.toMillis(1));

		JobScheduler jobScheduler = getJobScheduler(context);
		jobScheduler.schedule(builder.build());
	}

	/**
	 * Busca a próxima aula a ser notificada e agenda o job
	 *
	 * @param controller
	 * @param context
	 */
	public void agendarProximoJob(NotificationController controller, Context context) {
		Notificacao proxima = controller.getProximaNotificacao();
		long when = TempoUtils.millisTempoNotificacao(proxima);
		agendarJob(when, context);
	}


	private void agendarJobSeguinte(@NonNull Notificacao Anterior, Context context, NotificationController ctlNotificacao) {
		Notificacao Seguinte = ctlNotificacao.getNotificacaoSeguinte(Anterior);

		long when;
		if (Seguinte != null) {
			when = TempoUtils.millisTempoNotificacao(Seguinte);
		} else {
			when = TempoUtils.millisTempoNotificacao(Anterior);
			when += TimeUnit.DAYS.toMillis(7); // agenda para daqui a 7 dias
		}
		agendarJob(when, context);
	}



	private Notificacao acionarProximoAlarme(Context context, NotificationController ctlNotificacao) {
		Notificacao a = ctlNotificacao.getProximaNotificacao();
		if (a != null) {
			new AlarmReceiver().agendarAlarme(a, context);
		}
		return a;
	}


	public void removerJob(@NonNull Notificacao a, Context context, NotificationController ctlNotificacao) {
		long when = TempoUtils.millisTempoNotificacao(a) - TEMPO_FOLGA;

		List<JobInfo> jobs = getJobScheduler(context).getAllPendingJobs();
		for (JobInfo job : jobs) {
			if (job.getIntervalMillis() == when) {
				getJobScheduler(context).cancel(job.getId());
				agendarJobSeguinte(a, context, ctlNotificacao);
			}
		}
	}


	@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
	private class JobTask extends AsyncTask<JobParameters, Void, JobParameters> {
		private final JobService jobService;

		public JobTask(JobService jobService) {
			this.jobService = jobService;
		}

		@Override
		protected JobParameters doInBackground(JobParameters... params) {
			Context context = getApplicationContext();

			// aciona o alarme da próxima aula
			NotificationController ctlNotificacao = new NotificationController(context);
			Notificacao proxima = acionarProximoAlarme(context, ctlNotificacao);
			agendarJobSeguinte(proxima, context, ctlNotificacao);

			return params[0];
		}

		@Override
		protected void onPostExecute(JobParameters jobParameters) {
			jobService.jobFinished(jobParameters, false);
		}
	}


	public JobScheduler getJobScheduler(Context context) {
		JobScheduler jobScheduler = null;

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			jobScheduler = context.getSystemService(JobScheduler.class);
		} else {
			jobScheduler = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
		}
		return jobScheduler;
	}


}
