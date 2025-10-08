package com.montanez;

import java.net.http.HttpResponse;
import java.util.logging.Logger;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.TimerTrigger;
import com.montanez.util.HttpUtil;

public class CallFunction {

    @FunctionName("triggerHttpCall")
    public void generateSitemap(
            ExecutionContext context,
            @TimerTrigger(name = "scheduledCall", schedule = "%CRON_EXPRESSION%") String timerInfo) {
        Logger logger = context.getLogger();
        logger.info("Calling url");

        String url = System.getenv("APPLICATION_URL");
        String path = System.getenv("APPLICATION_PATH");

        if (url == null || path == null) {
            logger.severe("Missing values");
            return;
        }

        HttpResponse<String> response = HttpUtil.sendPostRequest(url, path, "{}");

        if (response == null) {
            logger.info("Error sending request.");
            return;
        }

        logger.info("Sent Request: " + response.statusCode() + " - " + response.body());
    }

}
