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
package org.more.hypha;
import java.util.HashMap;
import org.more.hypha.EventManager.Params;
import org.more.hypha.EventManager.Sequence;
/**
 * �¼���һ��֪ͨ���ƣ�ʹ���¼����ܿ����������̵�ִ�С�����ȴ����ͨ���¼���֪�ڲ��Ĺ���״̬��
 * �ýӿڱ�ʾ����һ��{@link EventManager}���Ա�ʶ�������¼���
 * @version 2010-10-10
 * @author ������ (zyc@byshell.org)
 */
public abstract class Event {
    private static HashMap<Class<?>, Event> eventMap = new HashMap<Class<?>, Event>();
    public Event() {
        if (eventMap.containsKey(this.getClass()) == false)
            eventMap.put(this.getClass(), this);
    }
    public static Event getEvent(Class<? extends Event> eventType) {
        return eventMap.get(eventType);
    }
    /**���¼�����ת��Ϊ{@link Params}���Ͷ���*/
    public abstract Params toParams(Sequence eventSequence);
};