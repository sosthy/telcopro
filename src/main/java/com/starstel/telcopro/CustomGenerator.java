package com.starstel.telcopro;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.stream.Stream;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

import com.starstel.telcopro.stocks.entities.Mouvment;

public class CustomGenerator implements IdentifierGenerator, Configurable 
{

	private String prefix;
	
	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object obj) throws HibernateException 
	{
		String query = String.format("select %s from %s", session.getEntityPersister(obj.getClass().getName(), obj)
				.getIdentifierPropertyName(), obj.getClass().getSimpleName());
		
		List<String> ids = session.createQuery(query).getResultList();
		
		Long max = ids.stream().map(o -> o.substring(o.indexOf("-") + 1)).mapToLong(Long::parseLong).max().orElse(0L);
		
		if(obj instanceof Mouvment) {
			Mouvment mv = (Mouvment) obj;
			prefix = mv.getMouvmentType().getName().substring(0, 2);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ddMMyykkmm");
			String dateAsString = simpleDateFormat.format(new Date());
			prefix += "*" + dateAsString;
		}
 
        return prefix + "-" + (max + 1);
	}

	@Override
	public void configure(Type type, Properties properties, ServiceRegistry serviceRegistry) throws MappingException 
	{
		prefix = properties.getProperty("prefix");
	}

}
