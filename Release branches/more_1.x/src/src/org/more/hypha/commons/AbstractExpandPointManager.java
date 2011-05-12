/*
 * Copyright 2008-2009 the original author or authors.
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
 */
package org.more.hypha.commons;
import java.util.ArrayList;
import java.util.List;
import org.more.hypha.ExpandPoint;
import org.more.hypha.ExpandPointManager;
import org.more.hypha.context.AbstractDefineResource;
/**
 * ���ฺ����������ò���ִ����չ��Ļ��ࡣ
 * @version 2011-1-14
 * @author ������ (zyc@byshell.org)
 */
public abstract class AbstractExpandPointManager implements ExpandPointManager {
    private AbstractDefineResource defineResource = null;
    private List<ExpandPoint>      expandList     = new ArrayList<ExpandPoint>();
    //
    public void init(AbstractDefineResource defineResource) {
        this.defineResource = defineResource;
    }
    /**����{@link AbstractDefineResource}����*/
    protected AbstractDefineResource getDefineResource() {
        return this.defineResource;
    }
    public Object exePointOnSequence(Class<? extends ExpandPoint> type, Object[] params) {
        Object returnObj = null;
        for (ExpandPoint ep : expandList)
            if (type.isInstance(ep) == true)
                returnObj = ep.doIt(returnObj, params);
        return returnObj;
    }
    public Object exePointOnReturn(Class<? extends ExpandPoint> type, Object[] params) {
        Object returnObj = null;
        for (ExpandPoint ep : expandList)
            if (type.isInstance(ep) == true) {
                returnObj = ep.doIt(returnObj, params);
                if (returnObj != null)
                    break;
            }
        return returnObj;
    }
    public void regeditExpandPoint(ExpandPoint point) {
        this.expandList.add(point);
    }
};