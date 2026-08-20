/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dtaxintegration.session;

import com.dimata.util.Formater;
import java.util.Date;

/**
 *
 * @author IanRizky
 */
public class DTaxIntegrationManagerAutoBphtb {

    public static boolean running = false;
	
	public static String erorStatus = "";

    public static DTaxIntegrationMonitor lck = new DTaxIntegrationMonitor();

    

    public DTaxIntegrationManagerAutoBphtb() {

    }

    public void startMonitor() {

        if(running) return;
			DTaxIntegrationManagerAutoBphtb objMan = new  DTaxIntegrationManagerAutoBphtb();

            Thread thLocker = new Thread(new dTaxMonitorAutoBphtb());

            thLocker.setDaemon(false);

            running = true;

            thLocker.start();

    }





    public void stopMonitor() {
		
        running = false;
        Date newDate = new Date();
        System.out.println("auto upload stopped .... datae/time : "+Formater.formatDate(newDate, "dd-MM-yyyy kk:mm"));
		erorStatus="";
    }

	public String getErorStatus() {

        return erorStatus;

    }



    public boolean getStatus() {

        return running;

    }

}
