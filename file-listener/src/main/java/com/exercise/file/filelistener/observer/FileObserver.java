package com.exercise.file.filelistener.observer;

import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.exercise.file.filelistener.listener.FileListener;

@Component
public class FileObserver {

	@Autowired
	private FileListener fileListener;

	@PostConstruct
	public void init() {
		String rootDir = "/home/siva/file-listener-files";
		long interval = TimeUnit.SECONDS.toMillis(30);

		IOFileFilter filter = FileFilterUtils.suffixFileFilter(".csv");

		FileAlterationObserver observer = new FileAlterationObserver(rootDir, filter);
		observer.addListener(fileListener);

		FileAlterationMonitor monitor = new FileAlterationMonitor(interval, observer);

		try {
			monitor.start();
			System.out.println("***********Monitoring*********");
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}
