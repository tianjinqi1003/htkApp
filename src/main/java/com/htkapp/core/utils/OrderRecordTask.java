package com.htkapp.core.utils;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.TimerTask;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.htkapp.modules.merchant.pay.service.OrderRecordService;

@Controller
@RequestMapping(value="/timerTask")
public class OrderRecordTask extends TimerTask {
	
	@Resource
	private OrderRecordService orderRecordService;

	@Override
	public void run() {
		// TODO Auto-generated method stub

		try {
			Desktop dt = Desktop.getDesktop();
			try {
				dt.browse(new URI("http://127.0.0.1:8088/htkApp/merchant/goAutoEnterReceipt"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//orderRecordService.autoEnterReceipt();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
