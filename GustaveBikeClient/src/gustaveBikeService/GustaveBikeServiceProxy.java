package gustaveBikeService;

public class GustaveBikeServiceProxy implements gustaveBikeService.GustaveBikeService {
  private String _endpoint = null;
  private gustaveBikeService.GustaveBikeService gustaveBikeService = null;
  
  public GustaveBikeServiceProxy() {
    _initGustaveBikeServiceProxy();
  }
  
  public GustaveBikeServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initGustaveBikeServiceProxy();
  }
  
  private void _initGustaveBikeServiceProxy() {
    try {
      gustaveBikeService = (new gustaveBikeService.GustaveBikeServiceServiceLocator()).getGustaveBikeService();
      if (gustaveBikeService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)gustaveBikeService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)gustaveBikeService)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (gustaveBikeService != null)
      ((javax.xml.rpc.Stub)gustaveBikeService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public gustaveBikeService.GustaveBikeService getGustaveBikeService() {
    if (gustaveBikeService == null)
      _initGustaveBikeServiceProxy();
    return gustaveBikeService;
  }
  
  public java.lang.String[] getBikesToBuy() throws java.rmi.RemoteException{
    if (gustaveBikeService == null)
      _initGustaveBikeServiceProxy();
    return gustaveBikeService.getBikesToBuy();
  }
  
  public java.lang.String sayHello() throws java.rmi.RemoteException{
    if (gustaveBikeService == null)
      _initGustaveBikeServiceProxy();
    return gustaveBikeService.sayHello();
  }
  
  public gustaveBikeService.GustaveBike buyBike(int bikeID, int userID, java.lang.String currencyType) throws java.rmi.RemoteException{
    if (gustaveBikeService == null)
      _initGustaveBikeServiceProxy();
    return gustaveBikeService.buyBike(bikeID, userID, currencyType);
  }
  
  public java.lang.String listCurrencies() throws java.rmi.RemoteException{
    if (gustaveBikeService == null)
      _initGustaveBikeServiceProxy();
    return gustaveBikeService.listCurrencies();
  }
  
  public void addFounds(int userID, long founds) throws java.rmi.RemoteException{
    if (gustaveBikeService == null)
      _initGustaveBikeServiceProxy();
    gustaveBikeService.addFounds(userID, founds);
  }
  
  public long getUsersFounds(int userID) throws java.rmi.RemoteException{
    if (gustaveBikeService == null)
      _initGustaveBikeServiceProxy();
    return gustaveBikeService.getUsersFounds(userID);
  }
  
  public boolean addToCard(int userID, int bikeID) throws java.rmi.RemoteException{
    if (gustaveBikeService == null)
      _initGustaveBikeServiceProxy();
    return gustaveBikeService.addToCard(userID, bikeID);
  }
  
  public gustaveBikeService.GustaveBike[] payBikesInCard(int userID, java.lang.String currencyType) throws java.rmi.RemoteException{
    if (gustaveBikeService == null)
      _initGustaveBikeServiceProxy();
    return gustaveBikeService.payBikesInCard(userID, currencyType);
  }
  
  public boolean removeFromCard(int userID, int bikeID) throws java.rmi.RemoteException{
    if (gustaveBikeService == null)
      _initGustaveBikeServiceProxy();
    return gustaveBikeService.removeFromCard(userID, bikeID);
  }
  
  public java.lang.String[] getCard(int userID) throws java.rmi.RemoteException{
    if (gustaveBikeService == null)
      _initGustaveBikeServiceProxy();
    return gustaveBikeService.getCard(userID);
  }
  
  
}