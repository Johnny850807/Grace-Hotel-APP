package com.ood.clean.waterball.gracehotel.Model;



public interface Serializer {
    public String serialize(Object object);
    public <T> T deserialize(String data, Class<T> clazz);
}
