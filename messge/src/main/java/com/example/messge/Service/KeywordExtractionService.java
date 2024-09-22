package com.example.messge.Service;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.nlp.v20190408.NlpClient;
import com.tencentcloudapi.nlp.v20190408.models.TextWritingRequest;
import com.tencentcloudapi.nlp.v20190408.models.TextWritingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KeywordExtractionService {

    private final String secretId = System.getenv("TENCENTCLOUD_SECRET_ID"); // 从环境变量获取
    private final String secretKey = System.getenv("TENCENTCLOUD_SECRET_KEY");

    public String extractKeywords(String text) {
        try {
            Credential cred = new Credential(secretId, secretKey);
            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("nlp.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            NlpClient client = new NlpClient(cred, "ap-shanghai", clientProfile);

            TextWritingRequest req = new TextWritingRequest();
            req.setText(text);

            TextWritingResponse resp = client.TextWriting(req);
            return TextWritingResponse.toJsonString(resp); // 返回关键词的 JSON 字符串
        } catch (TencentCloudSDKException e) {
            e.printStackTrace();
            return null;
        }
    }
}
