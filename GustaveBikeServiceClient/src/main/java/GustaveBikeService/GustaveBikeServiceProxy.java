package GustaveBikeService;

public class GustaveBikeServiceProxy implements GustaveBikeService.GustaveBikeService {
  private String _endpoint = null;
  private GustaveBikeService.GustaveBikeService gustaveBikeService = null;
  
  public GustaveBikeServiceProxy() {
    _initGustaveBikeServiceProxy();
  }
  
  public GustaveBikeServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initGustaveBikeServiceProxy();
  }
  
  private void _initGustaveBikeServiceProxy() {
    try {
      gustaveBikeService = (new GustaveBikeService.GustaveBikeServiceServiceLocator()).getGustaveBikeService();
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
  
  public GustaveBikeService.GustaveBikeService getGustaveBikeService() {
    if (gustaveBikeService == null)
      _initGustaveBikeServiceProxy();
    return gustaveBikeService;
  }
  
  
}