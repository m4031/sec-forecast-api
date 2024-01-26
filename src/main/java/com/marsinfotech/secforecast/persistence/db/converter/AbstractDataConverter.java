package com.marsinfotech.secforecast.persistence.db.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

public abstract class AbstractDataConverter<I, O> implements DataConverter<I, O> {
    private static final Logger logger = LoggerFactory.getLogger(AbstractDataConverter.class);
    private final Class<I> inputClass;
    private final Class<O> outputClass;
    private boolean initialized = false;
    private final List<Mapping<?, ?>> mappers = new ArrayList<>();

    private class Mapping<A, B>{
        private final Function<I, A> getter;
        private final Function<A, B> mapping;
        private final BiConsumer<O, B> setter;

        public Mapping(Function<I, A> getter, Function<A, B> mapping, BiConsumer<O, B> setter){
            this.getter = getter;
            this.setter = setter;
            this.mapping = mapping;
        }

        public void apply(I input, O output){
            A inputField = null;
            try{
                inputField = getter.apply(input);
            }catch (Exception ex){
                logger.info("Error in getter : {}", ex);
            }
            if(inputField != null){
                B outputField = null;
                try{
                    outputField = mapping.apply(inputField);
                }catch (Exception ex){
                    logger.info("Error in mapping : {}", ex);
                }

                if(outputField != null){
                    try{
                        if(outputField instanceof  Double && Double.isNaN((Double)outputField) ||
                                outputField instanceof  Float && Float.isNaN((Float) outputField)){
                            logger.info("Skipping NaN");
                        }else{
                            setter.accept(output, outputField);
                        }
                    }catch (Exception ex){
                        logger.info("Error in setter");
                    }
                }
            }
        }
    }

    public AbstractDataConverter(Class<I> inputClass, Class<O> outputClass){
        this.inputClass = inputClass;
        this.outputClass = outputClass;
    }

    public abstract O createOutput(I input);
    public abstract void init();

    public void doConvert(I input, O output){
        doMapping(input, output);
    }

    @Override
    public O convert(I input) {
        O output = createOutput(input);
        doConvert(input, output);
        return output;
    }

    public void doMapping(I input, O output){
        if(input == null){
            throw new NullPointerException("Invalid input");
        }
        if(output == null){
            throw new NullPointerException("Invalid output");
        }

        for(Mapping<?, ?> mapping : mappers){
            try{
                mapping.apply(input, output);
            }catch (Throwable ex){
                logger.info("Mapping failed for input Object:{} ", input);
            }
        }
    }

    protected <A> void map(Function<I, A> getter, BiConsumer<O, A> setter){
        map(getter, Function.identity(), setter);
    }

    protected <A> void map(Function<I, A> getter, A defaultValue, BiConsumer<O, A> setter){
        map ( i ->{
            A a = getter.apply(i);
            return a == null ? defaultValue : a;
        }, setter);
    }

    protected <A, B> void map(Function<I, A> getter, Function<A, B> mapping, BiConsumer<O, B> setter){
        mappers.add(new Mapping<A, B> (getter, mapping, setter));
    }

    public boolean isInitialized() {
        return initialized;
    }

    public void setInitialized(boolean initialized) {
        this.initialized = initialized;
    }
}
