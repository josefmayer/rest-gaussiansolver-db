package jettyJerseyRest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import data.InData;
import data.Result;
import dbClients.MySQLClient_2;
import parallelGaussian.ParallelGaussianEliminationNet_Rest;

import java.io.IOException;
import java.util.List;

import static java.lang.Math.sqrt;


//@Path("home")
@Path("")
public class RestService {


    //MySQLClient_1 dbClient = new MySQLClient_1();
    MySQLClient_2 dbClient = new MySQLClient_2();
    //MongoDBClient dbClient = new MongoDBClient();

    public RestService(){
   }

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public String getMethod() {
        StringBuffer sb = new StringBuffer();
        List<Object> resultList = null;
        resultList = dbClient.getAll();
        for (Object obj : resultList){
            sb.append(obj);
            sb.append("\n");
        }
        return sb.toString();
    }


    @POST
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postMethodd(String jsonStringInData) {

        //Json to Java
        InData inData = new InData();
        Result result = new Result();
        ObjectMapper mapper = new ObjectMapper();
        try {
            inData = mapper.readValue(jsonStringInData, InData.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (inData.a.length == 0 || inData.b.length == 0){
            return Response.serverError().entity("Input argument not found\n").build();
        }

        if (sqrt (inData.a.length) != inData.b.length){
            return Response.serverError().entity("Input argument length do not match\n").build();
        }

        // Gaussian Calculation
        ParallelGaussianEliminationNet_Rest pg = new ParallelGaussianEliminationNet_Rest();
        String[] args = new String[1];
        args[0] = "2";

        pg.execGaussianElimination_2(args, inData, result);

        if (result.result.length != inData.b.length){
            return Response.serverError().entity("GaussianElimination not succesful\n").build();
        }

        //Java to Json
        String jsonStringResult = "";
        try {
            jsonStringResult = mapper.writeValueAsString(result.result);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        // store in database
        dbClient.insert(jsonStringInData, jsonStringResult);

        return Response.ok(jsonStringResult  + "\n", MediaType.APPLICATION_JSON).build();
    }
}