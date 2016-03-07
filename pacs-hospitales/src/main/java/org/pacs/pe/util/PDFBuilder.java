package org.pacs.pe.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.pacs.pe.app.controller.AbstractITextPdfView;
import org.pacs.pe.app.model.Estudio;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

public class PDFBuilder extends AbstractITextPdfView {
	 
    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document doc,
            PdfWriter writer, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // get data model which is passed by the Spring container
        Estudio estudio = (Estudio) model.get("estudio");
        
        LineSeparator line1= new LineSeparator();              
     
        
        LineSeparator line2 = new LineSeparator();              
         
        doc.add(new Paragraph("Departamento de Radiología"));
        doc.add(new Paragraph("Minsa"));
        //doc.add(new Paragraph("Dirección"));
        
        /*Paragraph referringPhysician = new Paragraph(50);
        referringPhysician.setAlignment(Element.ALIGN_RIGHT);
        referringPhysician.add("«referringPhysician»");
        doc.add(referringPhysician);
    	*/
        
        Paragraph nombrePaciente= new Paragraph(50);
        nombrePaciente.setAlignment(Element.ALIGN_LEFT);
        nombrePaciente.add("Nombre : " + estudio.getPat_name() + " " + estudio.getPat_name());
        doc.add(nombrePaciente);
        //doc.add(new Paragraph("Fecha de Nacimiento"));
        //doc.add(new Paragraph("Número de Acceso"));
        doc.add(new Paragraph("Lima, " + new SimpleDateFormat("MM-dd-yyyy").format(new Date())));
        //Label Medico
        
        Paragraph labelInforme = new Paragraph(20);
        Font fontlabelInforme= new Font(FontFamily.HELVETICA, 10, Font.BOLD);
        labelInforme.setAlignment(Element.ALIGN_CENTER);
        labelInforme.setFont(fontlabelInforme);
        labelInforme.add("INFORME");
        doc.add(labelInforme);
        Paragraph modalidadEstudy = new Paragraph(10);
        Font fontModalidadEstudy = new Font(FontFamily.HELVETICA, 8, Font.BOLD);
        modalidadEstudy.setAlignment(Element.ALIGN_CENTER);
        modalidadEstudy.setFont(fontModalidadEstudy);
        modalidadEstudy.add("Modalidad : " + estudio.getModality());
        doc.add(modalidadEstudy);
        
        doc.add(new Paragraph("Estudio Realizado por : " + estudio.getUsuario_modificacion()));
        
        
        //doc.add(new Paragraph(50,"Indicaciones : "));
       
        doc.add(new Paragraph(50,"Tecnica : " + estudio.getModality()));
        doc.add(new Paragraph(50,"Conclusión : "));
        doc.add(new Paragraph(20,estudio.getDiagnostico()));        
        doc.add(new Paragraph(50,"Atentamente, "));
        
        doc.add(new Paragraph(estudio.getUsuario_modificacion()));
        //------------------
        // define table header cell
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(BaseColor.BLUE);
        cell.setPadding(5);
 
    }
	
	/* @Override
	    protected void buildPdfDocument(Map<String, Object> model, Document doc,
	            PdfWriter writer, HttpServletRequest request, HttpServletResponse response)
	            throws Exception {
	       
		 try
			{
			 
			 
			  
			PdfReader pdfReader = new PdfReader("Titulo.pdf");
			//@SuppressWarnings("unused")
			//InputStream stream = getClass().getResourceAsStream("/HelloWorld.pdf");	
			 	 
			 	PdfStamper pdfStamper = new PdfStamper(pdfReader, new FileOutputStream("Rewritten Titulo2.pdf"));
				PdfContentByte content = pdfStamper.getUnderContent(1);//1 for the first page
				BaseFont bf = BaseFont.createFont(BaseFont.TIMES_ITALIC, BaseFont.CP1250, BaseFont.EMBEDDED);
				content.beginText();
				content.setFontAndSize(bf, 18);
				content.showTextAligned(PdfContentByte.ALIGN_CENTER, "JavaCodeGeeks", 250,650,0);
				content.endText();
			
			        PdfCopy copy = new PdfCopy new FileOutputStream("Titulo.pdf"));
			        document.open();
			        for (int i = 0; i < n;) {
			            copy.addPage(copy.getImportedPage(reader, ++i));
			        }
			        
				
				pdfStamper.close();
				pdfReader.close();
			}
			catch (IOException e)
			{
					e.printStackTrace();
			}
			catch (DocumentException e)
			{
					e.printStackTrace();
			}
	         
	    }*/
 
}