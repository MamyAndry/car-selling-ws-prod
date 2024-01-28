/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carselling.selling.utils;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.Properties;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Configurable;

/**
 *
 * @author sarobidy
 */
public class IdGenerator implements IdentifierGenerator, Configurable {     
    String prefix;
    String sequence;
    Integer max_length;

    public Integer getMax_length() {
            return max_length;
    }

    public void setMax_length(Integer max_length) {
            this.max_length = max_length;
    }

    public String getSequence() {
            return sequence;
    }

    public void setSequence(String sequence) {
            this.sequence = sequence;
    }
    
    public String getPrefix() {
            return prefix;
    }

    public void setPrefix(String prefix) {
            this.prefix = prefix;
    }

    @Override
    public Serializable generate(SharedSessionContractImplementor ssci, Object o) throws HibernateException {
        try{
                    String sql = String.format("select nextval('%s')" , this.getSequence());
                    Long response = ssci.createQuery(sql, Long.class).getSingleResult();
                    String repons_s = String.valueOf(response);
                    String filled = fillZero( Integer.valueOf(repons_s) );
                    String primaryKey = this.getPrefix() + filled;
                    return primaryKey;
        }catch(Exception e){
                    throw new HibernateException(e);
        }
    }
    
    String fillZero( Integer id ){
        String id_string = String.valueOf(id);
        int required = max_length - id_string.length() - this.getPrefix().length();
        for( int i = 1; i <= required ; i++ ){
                    id_string = "0" + id_string;
        }
        return id_string;        
    }

    @Override
    public void configure(Type type, Properties parameters, ServiceRegistry serviceRegistry) {
        this.setPrefix(parameters.getProperty("prefix"));
        this.setSequence(parameters.getProperty("sequence"));
        this.setMax_length(Integer.valueOf(parameters.getProperty("max_length")));
    }

    @Override
    public String value() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Autowire autowire() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean dependencyCheck() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean preConstruction() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }         
}
