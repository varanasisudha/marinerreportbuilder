package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement( name = "records" )
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlRecord {

    @XmlElement( name = "report")
    List<XmlReport> reports;

    public void setReports( List<XmlReport> reports )
    {
        this.reports = reports;
    }

    public List<XmlReport> getReports() {
        return reports;
    }

}
