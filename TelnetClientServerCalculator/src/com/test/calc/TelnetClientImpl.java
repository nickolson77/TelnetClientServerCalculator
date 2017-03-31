/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.test.calc;

import java.io.IOException;
import org.apache.commons.net.telnet.TelnetClient;

import com.test.calc.IOUtil;

public final class TelnetClientImpl {

	public TelnetClientImpl(String host, int port) {

		TelnetClient telnet = new TelnetClient();

		try {
			telnet.connect(host, port);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}

		IOUtil.readWrite(telnet.getInputStream(), telnet.getOutputStream(), System.in, System.out);

		try {
			telnet.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}

		System.exit(0);

	}

}
