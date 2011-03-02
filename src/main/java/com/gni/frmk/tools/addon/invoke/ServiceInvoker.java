package com.gni.frmk.tools.addon.invoke;

import com.wm.data.IData;
import com.wm.data.IDataCursor;
import com.wm.data.IDataFactory;
import com.wm.data.IDataUtil;
import com.wm.lang.ns.NSName;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 25/10/10
 * Time: 18:52
 * To change this template use File | Settings | File Templates.
 */
public abstract class ServiceInvoker {

    public static final Map<String, ?> EMPTY_DATA = new HashMap<String, Object>();

    protected final NSName name;
    private final Set<String> inputSignature;
    private final Set<String> outputSignature;

    ServiceInvoker(ServiceInvokerBuilder builder) {
        this.name = builder.name;
        this.inputSignature = builder.inputSignature;
        this.outputSignature = builder.outputSignature;
    }

    public final Map<String, ?> invoke(Map<String, ?> input) throws ServiceException {
        IData dataInput = prepareInput(input);
        IData dataOutput = invokeIfExist(dataInput);
        return prepareOutput(dataOutput);
    }

    public final Map<String, ?> invokeSingleInput(Object value) throws ServiceException {
        IData dataInput = IDataFactory.create(new Object[][]{{inputSignature.iterator().next(), value}});
        IData dataOutput = invokeIfExist(dataInput);
        return prepareOutput(dataOutput);
    }

    protected abstract IData invokeHandler(IData input) throws Exception;

    private IData prepareInput(Map<String, ?> input) {
        IData in = IDataFactory.create(inputSignature.size());
        IDataCursor curIn = in.getCursor();
        try {
            for (String key : inputSignature) {
                if (input.containsKey(key)) {
                    IDataUtil.put(curIn, key, input.get(key));
                }
            }
        } finally {
            curIn.destroy();
        }
        return in;
    }

    private IData invokeIfExist(IData input) throws ServiceException {
        if (!exist()) {
            throw new ServiceNotFoundException(name.getFullName());
        }
        try {
            return invokeHandler(input);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException(name.getFullName(), e);
        }
    }

    private Map<String, ?> prepareOutput(IData dataOutput) {
        Map<String, Object> out = new HashMap<String, Object>(outputSignature.size());
        IDataCursor curDataOutput = dataOutput.getCursor();
        try {
            for (String key : outputSignature) {
                Object value = IDataUtil.get(curDataOutput, key);
                if (value != null) {
                    out.put(key, value);
                }
            }
        } finally {
            curDataOutput.destroy();
        }
        return out;
    }

    public final String getFullName() {
        return name.getFullName();
    }

    public abstract boolean exist();

}
