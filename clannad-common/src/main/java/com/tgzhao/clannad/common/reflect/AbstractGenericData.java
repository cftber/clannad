package com.tgzhao.clannad.common.reflect;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by tgzhao on 2016/9/9.
 */
public abstract class AbstractGenericData<E extends AbstractGenericData, T extends Comparable<T>> implements Comparable<E> {

    private T data;

   /* public AbstractGenericData(ChildData childData) {
        this(childData.getPath(), childData.getData());
    }

    public AbstractGenericData(String path, byte[] bytes) {
        this.path = path;
        this.id = PathHelper.getEndPath(this.path);
        this.data = JsonHelper.fromJson(bytes, getGenericType());
    }

    public AbstractGenericData(String path, T data) {
        this.path = path;
        this.id = PathHelper.getEndPath(this.path);
        this.data = data;
    }*/

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    private Class<T> getGenericType() throws Exception {
        Type type = getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            return (Class<T>) parameterizedType.getActualTypeArguments()[1];
        }
        throw new Exception("");
//        throw new UnknownGenericTypeException();
    }

/*    public byte[] getDataBytes() {
        return JsonHelper.toBytes(data);
    }*/

    @Override
    public int compareTo(E e) {
        return data.compareTo((T) e.getData());
    }

    /*@Override
    public String toString() {
        return "GenericData{" +
                "data=" + JsonHelper.toJson(data) +
                '}';
    }*/

}
