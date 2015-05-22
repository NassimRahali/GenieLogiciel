/*
 * RAHALI Nassim
 * M18
 * 2014-2015
 */
package xml3.sax;

import java.util.Arrays;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import xml.utils.XMLConfigSearch;

/**
 *
 * @author Nassim
 */
public class MyContentHandler extends DefaultHandler
{
    private XMLConfigSearch xmlConfigSearch;
    private String lastEl;
    private List<String> attributes;
    private boolean flag;
    
    public XMLConfigSearch getXMLConfigSearch()
    {
        return  this.xmlConfigSearch;
    }
    
    @Override
    public void startDocument() throws SAXException
    {
        super.startDocument();
        this.xmlConfigSearch = new XMLConfigSearch();
        this.lastEl = "";
    }
    
    @Override
    public void endDocument() throws SAXException
    {
        super.endDocument();
    }
    
    @Override
    public void startElement(String uri, String localName,
      String qName, Attributes atr) throws SAXException
    {
        super.startElement(uri, localName, qName, atr);
        
        switch(localName)
        {
            case "queries":
                break;
            case "field":
                if(atr.getLength() > 0)
                    this.attributes = Arrays.asList(atr.getValue(0).split(","));
                break;
            case "random":
                this.xmlConfigSearch
                  .setRandomIdsNumber(Integer.parseInt(atr.getValue(0)));
                break;
            case "included":
                this.flag = true;
                break;
            case "excluded":
                this.flag = false;
                break;
        }
        
        this.lastEl = localName;
    } 
    
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException
    {
        super.endElement(uri, localName, qName);
        this.attributes = null;
    }
    
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException
    {
        super.characters(ch, start, length);
        
        String content = new String(ch, start, length);
        switch(this.lastEl)
        {
            case "id":
                this.xmlConfigSearch.addId(Integer.parseInt(content));
                break;
            case "field":
                if(this.flag)
                {
                    if (this.attributes != null && this.attributes.size() > 0)
                    {
                        for (String filter : this.attributes)
                        {
                            this.xmlConfigSearch.addProjection(content + "." + filter);
                        }
                    } 
                    else
                    {
                        this.xmlConfigSearch.addProjection(content);
                    }
                }
                else
                {
                    if (this.attributes != null && this.attributes.size() > 0)
                    {
                        for (String filter : this.attributes)
                        {
                            this.xmlConfigSearch.removeProjection(content + "." + filter);
                        }
                    } 
                    else
                    {
                        this.xmlConfigSearch.removeProjection(content);
                    }
                }
                break;
        }
    }
}
