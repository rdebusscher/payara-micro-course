package be.rubus.courses.payara.micro;

import com.hazelcast.core.HazelcastInstance;
import fish.payara.cdi.jsr107.impl.NamedCache;

import javax.cache.Cache;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/counter")
public class DataServlet extends HttpServlet {

    private static final String CACHE_KEY = "RequestCount";

    // Payara Extension, requires the payara-api dependency (for @NamedCache)
    @NamedCache(cacheName = "DataGrid JCache")
    @Inject
    private Cache<String, Long> examplesCache;

    // Use Hazelcast Directly
    // see https://docs.hazelcast.com/imdg/4.2/
    @Inject
    private HazelcastInstance instance;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String response = tick() + "\n";
        resp.getWriter().println(response);
    }

    private String tick() {
        // Store stuff in JSR 107 JCache

        Long cacheCount = examplesCache.get(CACHE_KEY);
        if (cacheCount == null) {
            cacheCount = 0L;
        }
        examplesCache.put(CACHE_KEY, cacheCount + 1);

        return String.format("\nCounter value %d", cacheCount);

    }
}
