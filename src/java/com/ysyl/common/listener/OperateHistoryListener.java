package com.ysyl.common.listener;

import ins.framework.utils.BeanUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.engine.SessionFactoryImplementor;
import org.hibernate.event.PostDeleteEvent;
import org.hibernate.event.PostDeleteEventListener;
import org.hibernate.event.PreUpdateEvent;
import org.hibernate.event.PreUpdateEventListener;
import org.hibernate.persister.entity.SingleTableEntityPersister;

public class OperateHistoryListener
  implements PreUpdateEventListener, PostDeleteEventListener
{
  private static final long serialVersionUID = 1L;
  private static final Log logger = LogFactory.getLog(OperateHistoryListener.class);
  public static final int INSERT = 1;
  public static final int UPDATE = 2;
  public static final int DELETE = 3;
  private static final Object[] ZERO_OBJECTS = new Object[0];
  private String currentSQL;
  private String currentHQL;
  private String operateTimeField;
  private List<String> ignoreTableOnDelete;
  private Map<String, List<Method>> methodMap;

  public OperateHistoryListener()
  {
    this.ignoreTableOnDelete = new ArrayList();
    this.methodMap = new ConcurrentHashMap(); }

  public String getCurrentHQL() {
    return this.currentHQL;
  }

  public void setCurrentHQL(String currentHQL) {
    this.currentHQL = currentHQL;
  }

  public String getCurrentSQL() {
    return this.currentSQL;
  }

  public void setCurrentSQL(String currentSQL) {
    this.currentSQL = currentSQL;
  }

  public String getOperateTimeField() {
    return this.operateTimeField;
  }

  public void setOperateTimeField(String operateTimeField) {
    this.operateTimeField = operateTimeField;
  }

  public List<String> getIgnoreTableOnDelete() {
    return this.ignoreTableOnDelete;
  }

  public void setIgnoreTableOnDelete(List<String> ignoreTableOnDelete) {
    this.ignoreTableOnDelete = ignoreTableOnDelete;
    for (int i = 0; i < ignoreTableOnDelete.size(); ++i)
      ignoreTableOnDelete.set(i, ((String)ignoreTableOnDelete.get(i)).toLowerCase());
  }

  private String getStringValue(Object obj)
  {
    String val = null;
    if (obj == null) {
      return null;
    }
    val = obj.toString();
    return val;
  }

  private String getStringValue(Method method, Object obj)
    throws IllegalArgumentException, IllegalAccessException, InvocationTargetException
  {
    Object value = method.invoke(obj, ZERO_OBJECTS);
    return getStringValue(value);
  }

  public void onPostDelete(PostDeleteEvent event) {
//    Session session = null;
//    Transaction tx = null;
//    try
//    {
//      String entity;
//      if (event.getPersister() instanceof SingleTableEntityPersister) {
//        entity = ((SingleTableEntityPersister)event.getPersister()).getTableName();
//      }
//      else {
//        entity = event.getPersister().getEntityName();
//      }
//
//      if (!(this.ignoreTableOnDelete.contains(entity.toLowerCase()))) {
//        UtiOperateHistory history = new UtiOperateHistory();
//        history.setOperateType(Integer.valueOf(3));
//        Object entityId = event.getId();
//        history.setEntity(entity);
//
//        if ((entityId.getClass().isPrimitive()) || (entityId instanceof Number) || (entityId instanceof String) || (entityId instanceof Date) || (entityId instanceof Boolean) || (entityId instanceof Character))
//        {
//          history.setEntityKey1(getStringValue(entityId));
//        } else {
//          Class entityClass = event.getEntity().getClass();
//          String key = entityClass.getName();
//          List getter = (List)this.methodMap.get(key);
//          if (getter == null) {
//            getter = new ArrayList();
//            try {
//              AttributeOverrides attrs = (AttributeOverrides)entityClass.getMethod("getId", new Class[0]).getAnnotation(AttributeOverrides.class);
//
//              Annotation[] annotations = attrs.value();
//              for (int i = 0; i < annotations.length; ++i) {
//                AttributeOverride annotation = (AttributeOverride)annotations[i];
//                getter.add(BeanUtils.getGetterMethod(entityId.getClass(), annotation.name()));
//              }
//            }
//            catch (Exception e) {
//              logger.warn(e.getMessage(), e);
//              getter = BeanUtils.getGetter(entityId.getClass());
//            }
//            this.methodMap.put(key, getter);
//          }
//
//          switch (getter.size())
//          {
//          case 8:
//            history.setEntityKey8(getStringValue((Method)getter.get(7), entityId));
//          case 7:
//            history.setEntityKey7(getStringValue((Method)getter.get(6), entityId));
//          case 6:
//            history.setEntityKey6(getStringValue((Method)getter.get(5), entityId));
//          case 5:
//            history.setEntityKey5(getStringValue((Method)getter.get(4), entityId));
//          case 4:
//            history.setEntityKey4(getStringValue((Method)getter.get(3), entityId));
//          case 3:
//            history.setEntityKey3(getStringValue((Method)getter.get(2), entityId));
//          case 2:
//            history.setEntityKey2(getStringValue((Method)getter.get(1), entityId));
//          case 1:
//            history.setEntityKey1(getStringValue((Method)getter.get(0), entityId));
//
//            break;
//          default:
//            logger.error("表" + history.getEntity() + "的主键字段太多（超过8个）");
//          }
//
//        }
//
//        session = event.getPersister().getFactory().openSession();
//        tx = session.getTransaction();
//        tx.begin();
//        session.save(history);
//        session.flush();
//        tx.commit();
//      }
//    } catch (Exception ex) {
//      ex.printStackTrace();
//      logger.error(ex.getMessage(), ex);
//      if (tx != null)
//        tx.rollback();
//    }
//    finally {
//      if (session != null)
//        session.close();
//    }
  }

  public boolean onPreUpdate(PreUpdateEvent event)
  {
    String[] names = event.getPersister().getPropertyNames();
    if ((this.operateTimeField == null) || (this.operateTimeField.trim().length() == 0)) {
      this.operateTimeField = "operateTime";
    }
    for (int i = 0; i < names.length; ++i) {
      if (this.operateTimeField.equalsIgnoreCase(names[i])) {
        event.getState()[i] = retrieveCurrentTimestamp(event.getPersister().getFactory());
        try
        {
          BeanUtils.forceSetProperty(event.getEntity(), this.operateTimeField, event.getState()[i]);
        }
        catch (NoSuchFieldException e) {
          if (logger.isErrorEnabled()) {
            logger.error(e.toString(), e);
          }
        }
        break;
      }
    }
    return false;
  }

  private Timestamp retrieveCurrentTimestamp(SessionFactoryImplementor factory)
  {
    Session tempSession = factory.openSession();
    Timestamp current = null;
    try
    {
      List list;
      if (this.currentHQL == null) {
        if (this.currentSQL == null) {
          try
          {
            String dbName = tempSession.connection().getMetaData().getDatabaseProductName().toLowerCase(Locale.US);

            if (dbName.indexOf("informix") != -1)
              this.currentSQL = "select current from systables";
            else if (dbName.indexOf("oracle") != -1)
              this.currentSQL = "select sysdate from dual";
            else if (dbName.indexOf("postgresql") != -1)
              this.currentSQL = "select now() from information_schema.tables";
            else if (dbName.indexOf("mysql") != -1){
              this.currentSQL = "select now() from dual";
            }
          }
          catch (HibernateException e) {
            if (logger.isErrorEnabled())
              logger.error(e.toString(), e);
          }
          catch (SQLException e) {
            if (logger.isErrorEnabled()) {
              logger.error(e.toString(), e);
            }
          }
        }
        SQLQuery query = tempSession.createSQLQuery(this.currentSQL);
        query.setMaxResults(1);
        list = query.list();
        current = (Timestamp)list.get(0);
      } else {
        Query query = tempSession.createQuery(this.currentHQL);
        query.setMaxResults(1);
        list = query.list();
        current = (Timestamp)list.get(0);
      }
    } finally {
      tempSession.close();
    }
    return current;
  }
}