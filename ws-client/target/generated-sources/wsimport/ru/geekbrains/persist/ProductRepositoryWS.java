
package ru.geekbrains.persist;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.10
 * Generated source version: 2.2
 * 
 */
@WebService(name = "ProductRepositoryWS", targetNamespace = "http://persist.geekbrains.ru/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface ProductRepositoryWS {


    /**
     * 
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "insert", targetNamespace = "http://persist.geekbrains.ru/", className = "ru.geekbrains.persist.Insert")
    @ResponseWrapper(localName = "insertResponse", targetNamespace = "http://persist.geekbrains.ru/", className = "ru.geekbrains.persist.InsertResponse")
    public void insert(
        @WebParam(name = "arg0", targetNamespace = "")
        Product arg0);

    /**
     * 
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "update", targetNamespace = "http://persist.geekbrains.ru/", className = "ru.geekbrains.persist.Update")
    @ResponseWrapper(localName = "updateResponse", targetNamespace = "http://persist.geekbrains.ru/", className = "ru.geekbrains.persist.UpdateResponse")
    public void update(
        @WebParam(name = "arg0", targetNamespace = "")
        Product arg0);

    /**
     * 
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "delete", targetNamespace = "http://persist.geekbrains.ru/", className = "ru.geekbrains.persist.Delete")
    @ResponseWrapper(localName = "deleteResponse", targetNamespace = "http://persist.geekbrains.ru/", className = "ru.geekbrains.persist.DeleteResponse")
    public void delete(
        @WebParam(name = "arg0", targetNamespace = "")
        long arg0);

    /**
     * 
     * @return
     *     returns java.util.List<ru.geekbrains.persist.Product>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "findAll", targetNamespace = "http://persist.geekbrains.ru/", className = "ru.geekbrains.persist.FindAll")
    @ResponseWrapper(localName = "findAllResponse", targetNamespace = "http://persist.geekbrains.ru/", className = "ru.geekbrains.persist.FindAllResponse")
    public List<Product> findAll();

    /**
     * 
     * @param arg0
     * @return
     *     returns ru.geekbrains.persist.Product
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "findById", targetNamespace = "http://persist.geekbrains.ru/", className = "ru.geekbrains.persist.FindById")
    @ResponseWrapper(localName = "findByIdResponse", targetNamespace = "http://persist.geekbrains.ru/", className = "ru.geekbrains.persist.FindByIdResponse")
    public Product findById(
        @WebParam(name = "arg0", targetNamespace = "")
        long arg0);

}
