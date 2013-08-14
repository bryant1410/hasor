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
package org.hasor.mvc.controller.plugins.result.support;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import javax.inject.Singleton;
import org.hasor.context.AppContext;
import org.hasor.mvc.controller.plugins.result.ResultProcess;
import com.google.inject.Binding;
import com.google.inject.Inject;
import com.google.inject.TypeLiteral;
/**
 * action���ܵ���ڡ�
 * @version : 2013-5-11
 * @author ������ (zyc@byshell.org)
 */
@Singleton
class ResultProcessManager {
    private AppContext            appContext  = null;
    private ResultProcessPropxy[] defineArray = null;
    //
    @Inject
    public ResultProcessManager(AppContext appContext) {
        ArrayList<ResultProcessPropxy> processList = new ArrayList<ResultProcessPropxy>();
        TypeLiteral<ResultProcessPropxy> PROCESS_DEFS = TypeLiteral.get(ResultProcessPropxy.class);
        for (Binding<ResultProcessPropxy> entry : appContext.getGuice().findBindingsByType(PROCESS_DEFS))
            processList.add(entry.getProvider().get());
        this.defineArray = processList.toArray(new ResultProcessPropxy[processList.size()]);
        this.appContext = appContext;
    }
    public ResultProcess getResultProcess(Annotation annoData) {
        for (ResultProcessPropxy define : defineArray)
            if (define.matchAnno(annoData) == true) {
                return define.getInstance(this.appContext);
            }
        return null;
    }
}