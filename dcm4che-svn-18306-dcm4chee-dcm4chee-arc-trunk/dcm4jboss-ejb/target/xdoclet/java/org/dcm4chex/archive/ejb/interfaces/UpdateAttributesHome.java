/*
 * Generated by XDoclet - Do not edit!
 */
package org.dcm4chex.archive.ejb.interfaces;

/**
 * Home interface for UpdateAttributes.
 * @xdoclet-generated at ${TODAY}
 * @copyright The XDoclet Team
 * @author XDoclet
 * @version 2.18.0-SNAPSHOT
 */
public interface UpdateAttributesHome
   extends javax.ejb.EJBHome
{
   public static final String COMP_NAME="java:comp/env/ejb/UpdateAttributes";
   public static final String JNDI_NAME="ejb/UpdateAttributes";

   public org.dcm4chex.archive.ejb.interfaces.UpdateAttributes create()
      throws javax.ejb.CreateException,java.rmi.RemoteException;

}
