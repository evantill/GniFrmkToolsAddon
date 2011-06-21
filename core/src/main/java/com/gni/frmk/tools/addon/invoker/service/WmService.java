package com.gni.frmk.tools.addon.invoker.service;

import com.gni.frmk.tools.addon.invoker.api.*;
import com.gni.frmk.tools.addon.invoker.converter.BooleanConverter;
import com.gni.frmk.tools.addon.invoker.converter.IntegerConverter;
import com.gni.frmk.tools.addon.invoker.converter.StringConverter;
import com.google.inject.Singleton;
import com.wm.data.*;
import com.wm.lang.ns.NSName;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.wm.lang.ns.NSName.create;

/**
 * Created by IntelliJ IDEA.
 * Date: 25/05/11
 * Time: 10:07
 *
 * @author: e03229
 */
@Singleton
public abstract class WmService<I extends ServiceInput, O extends ServiceOutput> implements Service<I, O> {

    protected static final IData EMPTY_IDATA = IDataFactory.create();
    protected static final IData[] EMPTY_IDATA_ARRAY = new IData[0];

    private final NSName serviceName;

    protected WmService(String serviceName) {
        this.serviceName = create(checkNotNull(serviceName));
    }

    public NSName getServiceName() {
        return serviceName;
    }

    @Override
    public final O invoke(I input, ServiceContext context) throws ServiceException {
        IData in = prepareInput(input);
        IData out = context.invoke(getServiceName(), in);
        return prepareOutput(out);
    }

    protected abstract IData prepareInput(I input) throws ServiceInputException;

    protected abstract O prepareOutput(IData output) throws ServiceOutputException;

    protected IData extractIData(IData data, String key, boolean mandatory, IData defaultValue) throws ServiceOutputException {
        IDataCursor cur = data.getCursor();
        try {
            IData result = IDataUtil.getIData(cur, key);
            if (result == null) {
                result = defaultValue;
            }
            if (mandatory && result == null) {
                throw new ServiceOutputException(this, String.format("missing required IData in %s", key));
            }
            return result;
        } finally {
            cur.destroy();
        }
    }

    protected IData[] extractIDataArray(IData data, String key, boolean mandatory, IData[] defaultValue) throws ServiceOutputException {
        IDataCursor cur = data.getCursor();
        try {
            IData[] result = IDataUtil.getIDataArray(cur, key);
            if (result == null) {
                result = defaultValue;

            }
            if (mandatory && result == null) {
                throw new ServiceOutputException(this, String.format("missing required IData array in %s", key));
            }
            return result;
        } finally {
            cur.destroy();
        }
    }

    protected String extractStringValue(IData data, String key, boolean mandatory, String defaultValue) throws ServiceOutputException {
        return extractSingleValue(data, StringConverter.singleton, key, mandatory, defaultValue);
    }

    protected Boolean extractBooleanValue(IData data, String key, boolean mandatory, Boolean defaultValue) throws ServiceOutputException {
        return extractSingleValue(data, BooleanConverter.singleton, key, mandatory, defaultValue);
    }

    protected Integer extractIntegerValue(IData data, String key, boolean mandatory, Integer defaultValue) throws ServiceOutputException {
        return extractSingleValue(data, IntegerConverter.singleton, key, mandatory, defaultValue);
    }

    protected <T> T extractSingleValue(IData data, ValueConverter<?, T> converter, String key, boolean mandatory, T defaultValue) throws ServiceOutputException {
        T result = defaultValue;
        IDataCursor cur = data.getCursor();
        try {
            Object value = IDataUtil.get(cur, key);
            if (value != null) {
                if (converter.canConvert(value)) {
                    result = converter.convert(value);
                } else {
                    throw new ServiceOutputException(this,
                            "can not convert " + key + " to " + converter.getResultType());
                }
            }
            if (result == null && mandatory) {
                throw new ServiceOutputException(this, String.format("missing required %s", key));
            }
        } finally {
            cur.destroy();
        }
        return result;
    }

    protected IData checkDataExist(IData data) throws ServiceOutputException {
        if (data == null) {
            throw new ServiceOutputException(this, String.format("missing data"));
        }
        return data;
    }
}
