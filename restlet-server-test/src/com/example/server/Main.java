package com.example.server;

import org.apache.commons.daemon.Daemon;
import org.apache.commons.daemon.DaemonContext;
import org.apache.commons.daemon.DaemonController;
import org.apache.commons.lang.NotImplementedException;

public class Main {
	public static void main(final String[] args) throws Exception {
		DaemonContext context = new DaemonContext() {

			public DaemonController getController() {
				new DaemonController() {

					public void shutdown() throws IllegalStateException {
						throw new NotImplementedException("jar version daemon doesn't support");
					}

					public void reload() throws IllegalStateException {
						throw new NotImplementedException("jar version daemon doesn't support");
					}

					public void fail(String message, Exception exception) throws IllegalStateException {
						throw new NotImplementedException("jar version daemon doesn't support");
					}

					public void fail(Exception exception) throws IllegalStateException {
						throw new NotImplementedException("jar version daemon doesn't support");
					}

					public void fail(String message) throws IllegalStateException {
						throw new NotImplementedException("jar version daemon doesn't support");
					}

					public void fail() throws IllegalStateException {
						throw new NotImplementedException("jar version daemon doesn't support");
					}
				};
				return null;
			}

			public String[] getArguments() {
				return args;
			}
		};

		Daemon daemon = new DaemonImpl();
		daemon.init(context);
		daemon.start();
	}
}