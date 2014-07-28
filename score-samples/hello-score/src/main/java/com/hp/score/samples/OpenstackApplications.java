package com.hp.score.samples;

import com.hp.oo.openstack.actions.ExecutionPlanBuilder;
import com.hp.score.api.ExecutionPlan;
import com.hp.score.api.Score;
import com.hp.score.events.EventBus;
import com.hp.score.events.ScoreEvent;
import com.hp.score.events.ScoreEventListener;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Date: 7/28/2014
 *
 * @author Bonczidai Levente
 */
public class OpenstackApplications {
	private final static Logger logger = Logger.getLogger(OpenstackApplications.class);

	@Autowired
	private Score score;

	@Autowired
	private EventBus eventBus;

	public static void main(String[] args) {
		OpenstackApplications app = loadApp();
		app.registerEventListeners();
		app.start();
	}

	private void start() {
		ExecutionPlanBuilder builder = new ExecutionPlanBuilder();
		builder.addStep("com.hp.oo.openstack.actions.HttpClientPostMock", "post", "1");
		builder.addStep("com.hp.oo.openstack.actions.HttpClientSendEmailMock", "sendEmail", "2");
		builder.addStep("com.hp.oo.openstack.actions.ReturnStepActions", "successStepAction", "null");

		ExecutionPlan executionPlan = builder.getExecutionPlan();

		Map<String, Serializable> executionContext = new HashMap<>();
		//for post
		executionContext.put("username", "userTest");
		executionContext.put("password", "passTest");
		executionContext.put("host", "hostTest");
		executionContext.put("url", "urlTest");
		//for sendEmail
		executionContext.put("receiver", "receiverTest");
		executionContext.put("title", "titleTest");
		executionContext.put("body", "bodyTest");

		score.trigger(executionPlan, executionContext);
	}

	private static OpenstackApplications loadApp() {
		ApplicationContext context = new ClassPathXmlApplicationContext("/META-INF/spring/openstackApplicationContext.xml");
		return context.getBean(OpenstackApplications.class);
	}

	private void registerEventListeners() {
		Set<String> handlerTypes = new HashSet<>();
		handlerTypes.add("type1");
		registerEventListener(handlerTypes);

		handlerTypes = new HashSet<>();
		handlerTypes.add("type2");
		registerEventListener(handlerTypes);

	}

	private void registerEventListener(Set<String> handlerTypes) {
		eventBus.subscribe(new ScoreEventListener() {
			@Override
			public void onEvent(ScoreEvent event) {
				logger.info("Listener invoked on type: " + event.getEventType() + " with data: " + event.getData());
			}
		}, handlerTypes);
	}
}
