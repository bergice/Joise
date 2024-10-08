/*
 * Copyright (C) 2016 Jason Taylor.
 * Released as open-source under the Apache License, Version 2.0.
 * 
 * ============================================================================
 * | Joise
 * ============================================================================
 * 
 * Copyright (C) 2016 Jason Taylor
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * ============================================================================
 * | Accidental Noise Library
 * | --------------------------------------------------------------------------
 * | Joise is a derivative work based on Josua Tippetts' C++ library:
 * | http://accidentalnoise.sourceforge.net/index.html
 * ============================================================================
 * 
 * Copyright (C) 2011 Joshua Tippetts
 * 
 *   This software is provided 'as-is', without any express or implied
 *   warranty.  In no event will the authors be held liable for any damages
 *   arising from the use of this software.
 * 
 *   Permission is granted to anyone to use this software for any purpose,
 *   including commercial applications, and to alter it and redistribute it
 *   freely, subject to the following restrictions:
 * 
 *   1. The origin of this software must not be misrepresented; you must not
 *      claim that you wrote the original software. If you use this software
 *      in a product, an acknowledgment in the product documentation would be
 *      appreciated but is not required.
 *   2. Altered source versions must be plainly marked as such, and must not be
 *      misrepresented as being the original software.
 *   3. This notice may not be removed or altered from any source distribution.
 */

package com.sudoplay.joise.module;

import com.sudoplay.joise.ModuleInstanceMap;
import com.sudoplay.joise.ModuleMap;
import com.sudoplay.joise.ModulePropertyMap;

public class ModuleScaleOffset extends
    SourcedModule {

  private ScalarParameter scale = new ScalarParameter(1.0);
  private ScalarParameter offset = new ScalarParameter(0.0);

  public ModuleScaleOffset() {
  }

  public ModuleScaleOffset(String id) {
    this();
    setId(id);
  }

  public ModuleScaleOffset(double scale, Module source) {
    this();
    setScale(scale);
    setSource(source);
  }

  public ModuleScaleOffset(String id, double scale, Module source) {
    this(id);
    setScale(scale);
    setSource(source);
  }

  public ModuleScaleOffset(String id, Module source, double scale, double offset) {
    this(id, scale, source);
    setOffset(offset);
  }

  public void setScale(double s) {
    this.scale.set(s);
  }

  public void setOffset(double o) {
    this.offset.set(o);
  }

  @SuppressWarnings("unused")
  public void setScale(Module s) {
    this.scale.set(s);
  }

  public void setOffset(Module o) {
    this.offset.set(o);
  }

  @SuppressWarnings("unused")
  public void setScale(ScalarParameter scalarParameter) {
    this.scale.set(scalarParameter);
  }

  @SuppressWarnings("unused")
  public void setOffset(ScalarParameter scalarParameter) {
    this.offset.set(scalarParameter);
  }

  @Override
  public double get(double x, double y) {
    return this.source.get(x, y) * this.scale.get(x, y) + this.offset.get(x, y);
  }

  @Override
  public double get(double x, double y, double z) {
    return this.source.get(x, y, z) * this.scale.get(x, y, z) + this.offset.get(x, y, z);
  }

  @Override
  public double get(double x, double y, double z, double w) {
    return this.source.get(x, y, z, w) * this.scale.get(x, y, z, w)
        + this.offset.get(x, y, z, w);
  }

  @Override
  public double get(double x, double y, double z, double w, double u, double v) {
    return this.source.get(x, y, z, w, u, v) * this.scale.get(x, y, z, w, u, v)
        + this.offset.get(x, y, z, w, u, v);
  }

  @Override
  public void setSeed(String seedName, long seed) {
    super.setSeed(seedName, seed);
    this.scale.setSeed(seedName, seed);
    this.offset.setSeed(seedName, seed);
  }

  @Override
  public void writeToMap(ModuleMap moduleMap) {
    ModulePropertyMap modulePropertyMap = new ModulePropertyMap(this);
    modulePropertyMap
        .writeScalar("offset", this.offset, moduleMap)
        .writeScalar("scale", this.scale, moduleMap)
        .writeScalar("source", this.source, moduleMap);
    moduleMap.put(this.getId(), modulePropertyMap);
  }

  @Override
  public Module buildFromPropertyMap(ModulePropertyMap modulePropertyMap, ModuleInstanceMap moduleInstanceMap) {
    this.setOffset(modulePropertyMap.readScalar("offset", moduleInstanceMap));
    this.setScale(modulePropertyMap.readScalar("scale", moduleInstanceMap));
    this.setSource(modulePropertyMap.readScalar("source", moduleInstanceMap));
    return this;
  }
}
