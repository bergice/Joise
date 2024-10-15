package com.sudoplay.joise.module;

import com.sudoplay.joise.ModuleInstanceMap;
import com.sudoplay.joise.ModuleMap;
import com.sudoplay.joise.ModulePropertyMap;

public class ModuleOverlaps extends Module {
    private ScalarParameter low = new ScalarParameter(0);
    private ScalarParameter high = new ScalarParameter(0);
    private ScalarParameter control = new ScalarParameter(0);
    private double value;

    public ModuleOverlaps() {
    }

    public ModuleOverlaps(String id) {
        this();
        setId(id);
    }

    public ModuleOverlaps(String id, Module controlSource, double value, Module highSource, Module lowSource) {
        this(id);
        setControlSource(controlSource); // where the control
        setValue(value); // match this value
        setHighSource(highSource); // keep this
        setLowSource(lowSource); // otherwise, keep this
    }

    public ModuleOverlaps(String id, Module controlSource, double value, Module highSource, double lowSource) {
        this(id);
        setControlSource(controlSource); // where the control
        setValue(value); // match this value
        setHighSource(highSource); // keep this
        setLowSource(lowSource); // otherwise, keep this
    }

    public ModuleOverlaps(String id, Module controlSource, double value, double highSource, Module lowSource) {
        this(id);
        setControlSource(controlSource); // where the control
        setValue(value); // match this value
        setHighSource(highSource); // keep this
        setLowSource(lowSource); // otherwise, keep this
    }

    public void setLowSource(double source) {
        this.low.set(source);
    }

    public void setHighSource(double source) {
        this.high.set(source);
    }

    public void setControlSource(double source) {
        this.control.set(source);
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setLowSource(Module source) {
        this.low.set(source);
    }

    public void setHighSource(Module source) {
        this.high.set(source);
    }

    public void setControlSource(Module source) {
        this.control.set(source);
    }

    @SuppressWarnings({"unused", "WeakerAccess"})
    public void setLowSource(ScalarParameter scalarParameter) {
        this.low.set(scalarParameter);
    }

    @SuppressWarnings({"unused", "WeakerAccess"})
    public void setHighSource(ScalarParameter scalarParameter) {
        this.high.set(scalarParameter);
    }

    @SuppressWarnings({"unused", "WeakerAccess"})
    public void setControlSource(ScalarParameter scalarParameter) {
        this.control.set(scalarParameter);
    }

    @Override
    public double get(double x, double y) {
        if (this.control.get(x, y) == value
                && this.high.get(x, y) > 0) {
            return this.high.get(x, y);
        } else {
            return this.low.get(x, y);
        }
    }

    @Override
    public double get(double x, double y, double z) {
        return 0;
    }

    @Override
    public double get(double x, double y, double z, double w) {
        return 0;
    }

    @Override
    public double get(double x, double y, double z, double w, double u, double v) {
        return 0;
    }

    @Override
    public void setSeed(String seedName, long seed) {
        this.low.setSeed(seedName, seed);
        this.high.setSeed(seedName, seed);
        this.control.setSeed(seedName, seed);
    }

    @Override
    public void writeToMap(ModuleMap moduleMap) {
        ModulePropertyMap modulePropertyMap = new ModulePropertyMap(this);
        modulePropertyMap
                .writeScalar("low", this.low, moduleMap)
                .writeScalar("high", this.high, moduleMap)
                .writeScalar("control", this.control, moduleMap)
                .writeDouble("threshold", this.value);
        moduleMap.put(this.getId(), modulePropertyMap);
    }

    @Override
    public Module buildFromPropertyMap(ModulePropertyMap modulePropertyMap, ModuleInstanceMap moduleInstanceMap) {
        this.setLowSource(modulePropertyMap.readScalar("low", moduleInstanceMap));
        this.setHighSource(modulePropertyMap.readScalar("high", moduleInstanceMap));
        this.setControlSource(modulePropertyMap.readScalar("control", moduleInstanceMap));
        this.setValue(modulePropertyMap.readDouble("threshold"));
        return this;
    }
}