package config;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {
    private static final ThreadLocal<Boolean> setupSuite = ThreadLocal.withInitial(() -> false);
    private static final ThreadLocal<Map<String, Object>> sharedData =
            ThreadLocal.withInitial(HashMap::new);
    private static final String GET_ERROR_MESSAGE =
            "Can't get data value with key %s. Current shared data %s";
    private ScenarioContext() {}
    public static ScenarioContext getInstance() {
        return InstanceHolder.INSTANCE;
    }
    public void setData(String key, Object value) {
        sharedData.get().put(key, value);
    }
    public void copyData(Map<String, Object> srcData) {
        sharedData.get().putAll(srcData);
    }
    public Boolean isContains(String key) {
        return sharedData.get().containsKey(key);
    }
    public boolean isSetupSuite() {
        return setupSuite.get();
    }
    public void setSetupSuite(boolean isSetupSuite) {
        setupSuite.set(isSetupSuite);
    }
    @SuppressWarnings("unchecked")
    public <T> T getData(String key) {
        if (sharedData.get().containsKey(key)) return (T) sharedData.get().get(key);
        String exceptionMsg = String.format(GET_ERROR_MESSAGE, key, getInstance());
        throw new IllegalAccessError(exceptionMsg);
    }
    @SuppressWarnings("unchecked")
    public <T> T getData(String key, boolean allowNullData) {
        if (sharedData.get().containsKey(key)) return (T) sharedData.get().get(key);
        if (allowNullData) return null;
        String exceptionMsg = String.format(GET_ERROR_MESSAGE, key, getInstance());
        throw new IllegalAccessError(exceptionMsg);
    }
    @Override
    public String toString() {
        return "TestContext [sharedData=" + sharedData.get() + "]";
    }
    public void setContext(String key, Object value) {
        getInstance().setData(key, value);
    }
    public <T> T getContext(String key) {
        return getInstance().getData(key);
    }
    @SuppressWarnings("unchecked")
    public <T> T getContext(String key, Object defaultValue) {
        try {
            return getInstance().getData(key);
        } catch (IllegalAccessError e) {
            return (T) defaultValue;
        }
    }
    public void clearAllDataInThreadContext() {
        sharedData.remove();
    }
    public void clearSetupSuiteFlag() {
        setupSuite.remove();
    }
    private static class InstanceHolder {
        private static final ScenarioContext INSTANCE = new ScenarioContext();
    }
}
