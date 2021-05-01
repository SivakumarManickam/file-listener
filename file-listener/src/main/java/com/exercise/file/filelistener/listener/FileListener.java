package com.exercise.file.filelistener.listener;

import java.io.File;

import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.exercise.file.filelistener.service.KafkaProducer;

@Component
public class FileListener extends FileAlterationListenerAdaptor{

	Logger logger = LoggerFactory.getLogger(FileListener.class);

	@Value("${kafka.topic.employee}")
	private String EMPLOYEE_TOPIC;

	@Value("${kafka.topic.customer}")
	private  String CUSTOMER_TOPIC;

	@Value("${kafka.topic.department}")
	private String DEPARTMENT_TOPIC;

	@Autowired
	KafkaProducer producer;

	@Override
	public void onFileCreate(File file) {

		String path = file.getAbsolutePath();
		logger.info("File Created: " + path);

		if (path.contains("emp")) {
			producer.publish(EMPLOYEE_TOPIC, path);
		} else if (path.contains("cust")) {
			producer.publish(CUSTOMER_TOPIC, path);
		} else if (path.contains("dept")) {
			producer.publish(DEPARTMENT_TOPIC, path);
		}
	}
}