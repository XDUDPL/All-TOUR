package com.tourdulich.tourdulich.Common;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BaseRequest<T> {
    String url;
    private final Class<T> type;
    private final Class<T[]> ListType;
    private static final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

    public BaseRequest(String url, Class<T> type, Class<T[]> listType) {
        this.type = type;
        this.url = url;
        this.ListType = listType;
    }

    public List<T> get() {
        ClientConfig clientConfig = new DefaultClientConfig();
        Client client = Client.create(clientConfig);
        WebResource webResource = client.resource(url);
        Builder builder = webResource.accept(MediaType.APPLICATION_JSON) //
                .header("content-type", MediaType.APPLICATION_JSON);
        ClientResponse response;
        response = builder.get(ClientResponse.class);
        if (response.getStatus() != 200) {
            System.err.println("Failed with HTTP Error code: " + response.getStatus());
            String error = response.getEntity(String.class);
            System.err.println("Error: " + error);
            return new ArrayList<>();
        }
        String data = response.getEntity(String.class);
        T[] list = gson.fromJson(data, ListType);
        List<T> result = Arrays.asList(list);
        if (!data.equals(gson.toJson(list))) {
            try {
                System.out.println(gson.toJson(list));
                throw new RequestMappingException("Cánh báo Lỗi mapping " + ListType.getSimpleName());
            } catch (RequestMappingException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public T get(int id) {
        ClientConfig clientConfig = new DefaultClientConfig();
        Client client = Client.create(clientConfig);
        WebResource webResource = client.resource(url + "/" + id);
        Builder builder = webResource.accept(MediaType.APPLICATION_JSON) //
                .header("content-type", MediaType.APPLICATION_JSON);
        ClientResponse response = builder.get(ClientResponse.class);
        if (response.getStatus() != 200) {
            System.err.println("Failed with HTTP Error code: " + response.getStatus());
            String error = response.getEntity(String.class);
            System.err.println("Error: " + error);
            return null;
        }
        String data = response.getEntity(String.class);
        return getT(gson, data);
    }

    public T post(T model) {
        Client client = Client.create();
        WebResource webResource = client.resource(url);
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        String data = gson.toJson(model);
        ClientResponse response = webResource.type("application/json").post(ClientResponse.class, data);
        if (response.getStatus() != 200) {
            System.err.println("Failed : HTTP error code : " + response.getStatus());
            String error = response.getEntity(String.class);
            System.err.println("Error: " + error);
            return null;
        }
        data = response.getEntity(String.class);
        return getT(gson, data);
    }

    private T getT(Gson gson, String data) {
        T result = gson.fromJson(data, type);
        if (!gson.toJson(result).equals(data)) {
            try {
                throw new RequestMappingException("Cánh báo Lỗi mapping " + type.getSimpleName());
            } catch (RequestMappingException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public T put(T model, int id) {
        Client client = Client.create();
        WebResource webResource = client.resource(url + "/" + id);
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        String data = gson.toJson(model);
        ClientResponse response = webResource.type("application/json").put(ClientResponse.class, data);
        if (response.getStatus() != 200) {
            System.err.println("Failed : HTTP error code : " + response.getStatus());
            String error = response.getEntity(String.class);
            System.err.println("Error: " + error);
            return null;
        }
        data = response.getEntity(String.class);
        return getT(gson, data);
    }

    public boolean delete(int id) {
        Client client = Client.create();
        WebResource webResource = client.resource(url + "/" + id);
        ClientResponse response = webResource.type("application/json").delete(ClientResponse.class);
        if (response.getStatus() != 200) {
            System.err.println("Failed : HTTP error code : " + response.getStatus());
            String error = response.getEntity(String.class);
            System.err.println("Error: " + error);
            return false;
        }
        return true;
    }
    public List<T> getByPropID(Integer id,String prop){
        ClientConfig clientConfig = new DefaultClientConfig();
        Client client = Client.create(clientConfig);
        WebResource webResource = client.resource(url+"/"+prop+"/"+id);
        Builder builder = webResource.accept(MediaType.APPLICATION_JSON) //
                .header("content-type", MediaType.APPLICATION_JSON);
        ClientResponse response;
        response = builder.get(ClientResponse.class);
        if (response.getStatus() != 200) {
            System.err.println("Failed with HTTP Error code: " + response.getStatus());
            String error = response.getEntity(String.class);
            System.err.println("Error: " + error);
            return new ArrayList<>();
        }
        String data = response.getEntity(String.class);
        T[] list = gson.fromJson(data, ListType);
        List<T> result = Arrays.asList(list);
        if (!data.equals(gson.toJson(list))) {
            try {
                throw new RequestMappingException("Cánh báo Lỗi mapping " + ListType.getSimpleName());
            } catch (RequestMappingException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
