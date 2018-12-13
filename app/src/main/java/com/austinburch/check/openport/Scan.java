package com.austinburch.check.openport;


import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.NetworkInfo;
import android.net.ConnectivityManager;
import android.os.StrictMode;

import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.location.Address;

import java.util.Enumeration;
import java.net.NetworkInterface;
import java.io.IOException;
import java.math.BigInteger;
import java.net.Inet4Address;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.Formatter;
import java.util.List;

import android.os.NetworkOnMainThreadException;
import java.net.UnknownHostException;
import java.net.InetAddress;


public class Scan extends AppCompatActivity{


    private static String connError;
    private WifiManager wifiManager;
    private WifiInfo connection;
    private TextView ipDisplay;
    private TextView ssidDisplay;
    private TextView macDisplay;
    private TextView bssidDisplay;
    private InetAddress ipAddress;
    //private String hostAddress;
    private String ssidView;
    private String macView;
    private String bssidView;
    private String address;
    private byte[] hostIp;
    private Button portScanBtn;

    //Display network info//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        ipDisplay = findViewById(R.id.addressDisplay);
        ssidDisplay = (TextView) findViewById(R.id.ssidDisplay);
        macDisplay = (TextView) findViewById(R.id.macDisplay);
        bssidDisplay = (TextView) findViewById(R.id.bssidDisplay);

        //Gather WIFI Info and display//
        wifiManager = (WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        connection = wifiManager.getConnectionInfo();



        //Turn Ip address into byte array and display





        //Get the rest of the wifi info
        String ipAddress = getAddress();
        ipDisplay.setText(ipAddress);
        ssidView = connection.getSSID();
        ssidDisplay.setText(ssidView);
        macView = connection.getMacAddress();
        macDisplay.setText(macView);
        bssidView = connection.getBSSID();
        bssidDisplay.setText(bssidView);


        portScanBtn = findViewById(R.id.btnPortScan);

        //public void onClick(View v){
          //  startActivity(new Intent(Scan.this.PortScanWindow.class));
        //}
    }

    private String getAddress(){
         String ip = "";
         try{
             for(Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces(); interfaces.hasMoreElements();)
             {
                 NetworkInterface iface = interfaces.nextElement();
                 for(Enumeration<InetAddress> addresses = iface.getInetAddresses(); addresses.hasMoreElements();)
                 {
                     InetAddress addr = addresses.nextElement();
                     if(!addr.isLoopbackAddress() && !addr.isLinkLocalAddress() && addr.isSiteLocalAddress()){
                        ip = addr.getHostAddress();
                     }


                    }


                }


         } catch (SocketException e){
             throw new RuntimeException(e);
            }
        return ip;
    }

   private String getIpAddress(String ssidView){
        String addr = "";
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try{
            InetAddress address = InetAddress.getLocalHost();
            addr = address.getHostAddress();
} catch (UnknownHostException ex){

}
    return addr;
}




    private void scanPorts(List<String> portNumber, ArrayAdapter<String> arrayAdapter) {
        //startPortScan = findViewById(R.id.btnPortScan);


                int firstPort = 1;
                int lastPort = 1024;
                int timeout;
                for (int i = firstPort; i <= lastPort; i++){
                    Socket socket = new Socket();
                    try{
                        socket.setTcpNoDelay(true);
                        byte[] bytes = BigInteger.valueOf(i).toByteArray();
                        socket.connect(new InetSocketAddress(InetAddress.getByAddress(bytes),7));
                    } catch (IOException ignored) {

                    } finally {
                        try{
                            socket.close();
                        } catch (IOException e){

                        }
                    }
                }

            }
        }






