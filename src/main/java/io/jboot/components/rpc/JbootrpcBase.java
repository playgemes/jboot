/**
 * Copyright (c) 2015-2020, Michael Yang 杨福海 (fuhai999@gmail.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.jboot.components.rpc;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class JbootrpcBase implements Jbootrpc {

    protected static final Map<String, Object> objectCache = new ConcurrentHashMap<>();


    @Override
    public <T> T serviceObtain(Class<T> serviceClass, JbootrpcReferenceConfig config) {
        String key = buildCacheKey(serviceClass,config);
        T object = (T) objectCache.get(key);
        if (object == null) {
            synchronized (this){
                if (objectCache.get(key) == null) {
                    object = onServiceCreate(serviceClass,config);
                    if (object != null){
                        objectCache.put(key,object);
                    }
                }
            }
        }
        return object;
    }

    public abstract  <T> T onServiceCreate(Class<T> serviceClass, JbootrpcReferenceConfig config);

    @Override
    public void onInit() {

    }

    @Override
    public void onInited() {

    }

    protected String buildCacheKey(Class serviceClass, JbootrpcReferenceConfig config){
        StringBuilder sb = new StringBuilder(serviceClass.getName());
        return sb.append(":").append(config.getGroup())
                .append(":").append(config.getVersion())
                .toString();
    }
}
