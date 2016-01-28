package com.codesanook.sample.googleauthentication;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collection;

public class Program {

    //get these from developer console
    private static final String SERVICE_ACCOUNT_EMAIL = "codesanook-test-service@codesanook-test.iam.gserviceaccount.com";
    private static final String P12_FILE_PATH = "c:/codesanook-test-service-key.p12";

    //1 Build service account credential.
    private static GoogleCredential createCredentail(Collection<String> scopes) throws GeneralSecurityException, IOException {

        HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
        File file = new File(P12_FILE_PATH);

        GoogleCredential credential = new GoogleCredential.Builder()
                .setTransport(httpTransport)
                .setJsonFactory(jsonFactory)
                .setServiceAccountId(SERVICE_ACCOUNT_EMAIL)

                .setServiceAccountScopes(scopes)
                .setServiceAccountPrivateKeyFromP12File(file)
                .build();

        return credential;
    }

    //2 get service scopes
    public static Collection<String> getScopes() {
        java.util.Set<String> set = new java.util.HashSet<String>();
        set.add("https://www.googleapis.com/auth/devstorage.read_only");
        return java.util.Collections.unmodifiableSet(set);
    }


    //3 use credential
    public static void main(String[] args) throws GeneralSecurityException, IOException {
        GoogleCredential credential = createCredentail(getScopes());
    }

}
