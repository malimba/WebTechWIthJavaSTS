package com.appsdeveloperblog.app.ws.Security;


import org.springframework.core.env.Environment;

public class SecurityConstants {

    public static final long Expiration_Time_In_Seconds = 432000;    // 5 days
    public static final String Token_Prefix = "Bearer ";
    public static final String Token_Header = "Authorization";
    public static final String Sign_Up_URL = "/users";
    public static final String Token_Secret = "bvgshg73hue7739349nfewywfw9wldsa73waada13948uewjew2d4f5z0s6xv";

    public  static String getTokenSecret() {
        Environment environment = (Environment) SpringApplicationContext.getBean("environment");
        return environment.getProperty("tokenSecret");
    }

}
