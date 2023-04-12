package be.rubus.courses.payara.micro.jaxrs.interceptor;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.WriterInterceptor;
import javax.ws.rs.ext.WriterInterceptorContext;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.zip.GZIPOutputStream;


@Provider
public class GZipWriterInterceptor implements WriterInterceptor {

    @Override
    public void aroundWriteTo(WriterInterceptorContext context)
            throws IOException, WebApplicationException {

        MultivaluedMap<String, Object> requestHeaders = context.getHeaders();
        List<Object> acceptEncoding = requestHeaders.get(HttpHeaders.ACCEPT_ENCODING);

        if (acceptEncoding != null) {
            // Compress if client accepts gzip encoding
            for (Object s : acceptEncoding) {
                if (s != null && s.toString().contains("gzip")) {

                    MultivaluedMap<String, Object> headers = context.getHeaders();
                    headers.add(HttpHeaders.CONTENT_ENCODING, "gzip");

                    //Capture the original outputStream
                    OutputStream outputStream = context.getOutputStream();
                    // Wrap it in a GZIPOutputStream
                    context.setOutputStream(new GZIPOutputStream(outputStream));

                    headers.remove(HttpHeaders.ACCEPT_ENCODING); // This is a request header, so remove it.
                    break;
                }
            }
        }
        context.proceed();
    }

}
