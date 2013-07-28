package com.swradioafrica.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.swradioafrica.model.ContentItem;
import com.swradioafrica.model.ContentItemDAO;

@SuppressWarnings("serial")
@Singleton
public class ServeRssServlet extends HttpServlet {

  @Inject private ContentItemDAO contentItemDAO;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    response.setContentType("text/xml; charset=UTF8");
    response.setCharacterEncoding("UTF-8");

    List<ContentItem> itemsByDate = contentItemDAO.getMostRecentItemsByDate(20);


    if (itemsByDate.size() == 0) {
      response.sendError(response.SC_NOT_FOUND, "No items in feed.");
    } else {
      response.setHeader("Cache-Control", "public, max-age=900");
      response.setHeader("Pragma", "Public");

      request.setAttribute("lastUpdated", itemsByDate.get(0).getPublishedDateRSS822());
      request.setAttribute("contentItems", itemsByDate);
      String destination = "/rss.jsp";
      RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
      rd.forward(request, response);
    }

  }

}
