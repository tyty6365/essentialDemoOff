package com.example.essentialdemo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "CivilServlet", value = "/CivilServlet")
public class CivilServlet extends HttpServlet {
    ArrayList<Class> civiee = new ArrayList<>();
    @Override

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User civiuser = (User) getServletContext().getAttribute("User");
        String className = request.getParameter("className");
        System.out.println("User: " + civiuser.eightNine);
        System.out.println("Dropping class:" + className);


        civiuser.removeClassFromSchedule(civiuser.eightNine,className);
        civiee = civiuser.scheduledClasses;
        request.setAttribute("civiee", civiee);

        // forward the request to the JSP page to display the updated scheduled classes
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/CivilSchedule.jsp");
        dispatcher.forward(request, response);
    }

    protected void redirection(HttpServletRequest request, HttpServletResponse response, ArrayList<Class> a) throws ServletException, IOException {
//
        request.setAttribute("civiee", a);
        if (a!=null) {
            RequestDispatcher dispatcherr = request.getRequestDispatcher("WEB-INF/CivilSchedule.jsp");
            dispatcherr.forward(request, response);
//
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ceClasses = request.getParameter("CiviE");
        User ceuser = (User) getServletContext().getAttribute("User");
        civiee = ceuser.scheduledClasses;
        if(ceClasses.equals("schedule")){
            redirection(request, response, civiee);
        }else if(ceClasses.equals("flowchart")) {
            RequestDispatcher flow = request.getRequestDispatcher("WEB-INF/flowchart.jsp");
            flow.forward(request, response);
        }else if(ceClasses.equals("jsp")){
            RequestDispatcher sched = request.getRequestDispatcher("WEB-INF/CivilEngineering.jsp");
            sched.forward(request, response);
        }
        else {
            String username = (String) getServletContext().getAttribute("Username");
//            System.out.println(username);

            Course newCourse = new Course("C:\\Users\\mimic\\IdeaProjects\\essentialDemoOff\\src\\main\\java\\com\\example\\essentialdemo\\classtxtFolder\\" + ceClasses+".txt");
            Class newClass = new Class(newCourse.name, newCourse.section, newCourse.creditHours, newCourse.days, newCourse.start, newCourse.morning, newCourse.end, newCourse.endMorning, newCourse.building, newCourse.room, newCourse.instructor, newCourse.prereqs);
            String message = ceuser.addClassToSchedule(newClass);
            System.out.println(message);
            civiee = ceuser.scheduledClasses;
            if(message.equals("Did not pass prereqcheck")) {
                request.setAttribute("message", "Did not pass prerequisites.");
            }else if(message.equals("Did not pass DiscrepancyCheck")){
                request.setAttribute("message", "Time Discrepancy not passed.");
            }else if(message.equals("Did not pass HoursCheck")) {
                request.setAttribute("message", "You went over your 19 credit hours limit");
            }else if (message.equals("Successfully added class to schedule!")){
                request.setAttribute("message", "Class Added Successfully!");
            }
            else if(message.equals("You have already scheduled this class")){
                request.setAttribute("message", "You have already scheduled this class!");
            }
            else if(message.equals("You have already taken this class.")){
                request.setAttribute("message", "You have already taken this class.");
            }



            RequestDispatcher dispatcherrr = request.getRequestDispatcher("WEB-INF/CivilEngineering.jsp");
            dispatcherrr.forward(request, response);

        }

    }
}

