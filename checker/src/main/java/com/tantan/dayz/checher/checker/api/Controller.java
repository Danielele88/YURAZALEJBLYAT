package com.tantan.dayz.checher.checker.api;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.brotli.dec.BrotliInputStream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class Controller {
    public void test() throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        try {

            HttpGet request = new HttpGet("https://api.cftools.cloud/app/v1/profile/5eee2201119b8954fe715b9b/overview");

            // add request headers
            request.addHeader("cookie", "cdn-auth=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjZnRvb2xzX2lkIjoiNWU5NGE2ZDI5Mzk1M2UxYTA0NjkwMzIyIiwiZXhwIjoxNzAzOTM0NTc1LCJpYXQiOjE2NzIzOTg1NzUsImp0aSI6ImNBcGpaTXB3NXZNSHhxZEJVSTZjLVEiLCJuYmYiOjE2NzIzOTg1NzV9.q0OgLoDt0s2ZjkTaXrqPDJPe-ymtA9tWqcgrlq14Yng; user_info=\"{\\\"profile\\\": {\\\"display_name\\\": \\\"direct-link\\\"\\054 \\\"avatar\\\": \\\"direct-link\\\"}\\054 \\\"user\\\": {\\\"cftools_id\\\": \\\"5e94a6d293953e1a04690322\\\"}\\054 \\\"status\\\": true}\"; session=.eJxtzEsOAiEQRdG9MJZOvfoAxTrcAA10YqIjnRn3LkkPdXxP7ju8Qg0T2IlYeyHHwMGC7kfRLLOLZoMK2sDE3FPpPoqlZllg3JoUauES-towMUdwFLoClUrVtLkxES3w-AVexTbWcoJ5AomECPlzuI0lbLq2NNjFTSYaaXIS5tXvz1Dx-QJIai_d.Y67TQg.vfST9r7FcnYBENtWOVT52qJRFKY");
            request.addHeader(HttpHeaders.USER_AGENT, "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.0.0 Safari/537.36");
            request.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");
            request.addHeader(HttpHeaders.CONNECTION, "keep-alive");
            request.addHeader(HttpHeaders.ACCEPT_ENCODING, "gzip, deflate, br");
            CloseableHttpResponse response = httpClient.execute(request);

            try {

                // Get HttpResponse Status
                System.out.println(response.getProtocolVersion());              // HTTP/1.1
                System.out.println(response.getStatusLine().getStatusCode());   // 200
                System.out.println(response.getStatusLine().getReasonPhrase()); // OK
                System.out.println(response.getStatusLine().toString());        // HTTP/1.1 200 OK

                HttpEntity entity = response.getEntity();
                BufferedReader bufferedReader;
                if (entity != null) {
                    if(response.getLastHeader("content-encoding").getValue().equals("br")) { // check if getting brotli compressed stream
                        bufferedReader = new BufferedReader(new InputStreamReader(new BrotliInputStream(response.getEntity().getContent())));
                    }
                    else {
                        bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                    }
                   String str = bufferedReader.lines().collect(Collectors.joining());
                    System.out.printf(str);
                }

            } finally {
                response.close();
            }
        }
        finally {
            httpClient.close();
        }
    }
}
