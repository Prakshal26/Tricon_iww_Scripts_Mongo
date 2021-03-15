package parser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import pojo.Person;
import xpath.classes.*;
import xpath.constants.XPathConstants;

import java.util.List;

public class ElementParse {

    public static void match(Element element, Person person) {

        String tagName = element.getTagName();
        switch (tagName) {

            case XPathConstants.NAME:
                Name.convert(element,person);
                break;

            case XPathConstants.PORTRAIT_IMAGE:
                person.setPortraitImage(Portrait.convert(element));
                break;

            case XPathConstants.QUALIFICATIONS:
                person.setQualifications(HandleAbbr.convert(element));
                break;
            case XPathConstants.NATIONALITY:
                person.setNationality(HandleAbbr.convert(element));
                break;
            case XPathConstants.PRESENTPOSITION:
                person.setPresentPosition(HandleAbbr.convert(element));
                break;
            case XPathConstants.NAMEATBIRTH:
                person.setNameAtBirth(HandleAbbr.convert(element));
                break;
            case XPathConstants.DATEOFBIRTH:
                person.setDateOfBirth(HandleAbbr.convert(element));
                break;
            case XPathConstants.PLACEOFBIRTH:
                person.setPlaceOfBirth(HandleAbbr.convert(element));
                break;
            case XPathConstants.DATEOFDEATH:
                person.setDateOfDeath(HandleAbbr.convert(element));
                break;
            case XPathConstants.PARENTAGE:
                person.setParentage(HandleAbbr.convert(element));
                break;
            case XPathConstants.IMMEDIATEFAMILY:
            case XPathConstants.EXTENDEDFAMILY:
                person.setFamily(HandleAbbr.convert(element));
                break;

            case XPathConstants.EDUCATION:
                person.setEducation(HandleAbbr.convert(element));
                break;
            case XPathConstants.CAREERPARA:
                person.setCareerPara(HandleAbbr.convert(element));
                break;
            case XPathConstants.HONOURSAWARDS:
                person.setHonoursAwards(HandleAbbr.convert(element));
                break;


            case XPathConstants.PROFESSION:
                //person.getProfession().add(element.getTextContent());
                person.setProfession(HandleAbbr.convert(element));
                break;

            case XPathConstants.FILMS:
                person.setFilms(Films.convert(element));
                break;
            case XPathConstants.PLAYS:
                person.setPlays(Films.convert(element));
                break;
            case XPathConstants.TV:
                person.setTv(Films.convert(element));
                break;
            case XPathConstants.MUSIC:
                person.setMusic(Films.convert(element));
                break;
            case XPathConstants.DANCE:
                person.setDance(Films.convert(element));
                break;
            case XPathConstants.ARTEXHIBITION:
                person.setArtExhibition(Films.convert(element));
                break;
            case XPathConstants.RADIO:
                person.setRadio(Films.convert(element));
                break;
            case XPathConstants.ACHIEVEMENTS:
                person.setAchievements(Films.convert(element));
                break;
            case XPathConstants.PUBLICATIONS:
                person.setPublications(Films.convert(element));
                break;
            case XPathConstants.LEISUREINTERESTS:
                person.setLeisureInterests(Films.convert(element));
                break;

            case XPathConstants.CONTACTDETAILS:
                List <String> stringList = ContactDetails.convert(element);
                if (stringList.get(0).equalsIgnoreCase("PUBLIC")) {
                    person.setContactDetails(stringList.get(1));
                }
                if (stringList.get(0).equalsIgnoreCase("MANAGEMENT")) {
                    person.setManagementAddress(stringList.get(1));
                }
                break;
            case XPathConstants.CROSSREFENTRY:
               CrossRefEntryParser.convert(element, person);
                break;
            default:
                break;

        }
    }

    public static Person parseFiles(Document doc) {

        Person person = new Person();

        doc.getDocumentElement().normalize();
        Node entryNode = doc.getDocumentElement();
        NodeList nodeList = entryNode.getChildNodes();

        Element entryElement = (Element) entryNode;


        if (entryElement.hasAttribute("DEAD")) {
            String deadVar = entryElement.getAttribute("DEAD");
            person.setDead(Integer.parseInt(deadVar));
        }

        if (entryElement.hasAttribute("GENDER")) {
            person.setGender(entryElement.getAttribute("GENDER"));
        }

        if (entryElement.hasAttribute("ID")) {
            String id = entryElement.getAttribute("ID").toLowerCase();
            person.setXmlId(id);
        }
        if (entryElement.hasAttribute("REGION")) {
            person.setRegion(entryElement.getAttribute("REGION"));
        }
        if (entryElement.hasAttribute("SUB-REGION")) {
            person.setSubRegion(entryElement.getAttribute("SUB-REGION"));
        }

        for (int i=0; i<nodeList.getLength();i++) {
            Node nNode = nodeList.item(i);

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element)nNode;
                match(element, person);
            }
        }

        return person;
    }
}
