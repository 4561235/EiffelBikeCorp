/**
 * GustaveBikeServiceServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package gustaveBikeService;

public class GustaveBikeServiceServiceLocator extends org.apache.axis.client.Service implements gustaveBikeService.GustaveBikeServiceService {

    public GustaveBikeServiceServiceLocator() {
    }


    public GustaveBikeServiceServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public GustaveBikeServiceServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for GustaveBikeService
    private java.lang.String GustaveBikeService_address = "http://localhost:8080/GustaveBikeService/services/GustaveBikeService";

    public java.lang.String getGustaveBikeServiceAddress() {
        return GustaveBikeService_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String GustaveBikeServiceWSDDServiceName = "GustaveBikeService";

    public java.lang.String getGustaveBikeServiceWSDDServiceName() {
        return GustaveBikeServiceWSDDServiceName;
    }

    public void setGustaveBikeServiceWSDDServiceName(java.lang.String name) {
        GustaveBikeServiceWSDDServiceName = name;
    }

    public gustaveBikeService.GustaveBikeService getGustaveBikeService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(GustaveBikeService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getGustaveBikeService(endpoint);
    }

    public gustaveBikeService.GustaveBikeService getGustaveBikeService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            gustaveBikeService.GustaveBikeServiceSoapBindingStub _stub = new gustaveBikeService.GustaveBikeServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getGustaveBikeServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setGustaveBikeServiceEndpointAddress(java.lang.String address) {
        GustaveBikeService_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (gustaveBikeService.GustaveBikeService.class.isAssignableFrom(serviceEndpointInterface)) {
                gustaveBikeService.GustaveBikeServiceSoapBindingStub _stub = new gustaveBikeService.GustaveBikeServiceSoapBindingStub(new java.net.URL(GustaveBikeService_address), this);
                _stub.setPortName(getGustaveBikeServiceWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("GustaveBikeService".equals(inputPortName)) {
            return getGustaveBikeService();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://gustaveBikeService", "GustaveBikeServiceService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://gustaveBikeService", "GustaveBikeService"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("GustaveBikeService".equals(portName)) {
            setGustaveBikeServiceEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
