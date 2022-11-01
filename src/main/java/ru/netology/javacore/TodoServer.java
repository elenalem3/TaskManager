package ru.netology.javacore;

import com.google.gson.Gson;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class TodoServer {
    protected int port;
    protected Todos todos;

    protected Deque<Operation> operations = new ArrayDeque<>();

    public TodoServer(int port, Todos todos) {
        this.port = port;
        this.todos = todos;
    }

    public void start() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Starting server at " + port + "...");
            Gson gson = new Gson();

            while (true) {
                try (
                        Socket socket = serverSocket.accept();
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        PrintWriter out = new PrintWriter(socket.getOutputStream());
                ) {
                    Operation operation = gson.fromJson(in.readLine(), Operation.class);
                    if (operation.getType() != TaskType.RESTORE) {
                        operations.add(operation);
                    } else {
                        operation = operations.pollLast();
                        if (operation.type == TaskType.ADD) {
                            operation.type = TaskType.REMOVE;
                        } else {
                            operation.type = TaskType.ADD;
                        }
                    }
                    if (operation.type == TaskType.ADD) {
                        todos.addTask(operation.task);
                    }
                    if (operation.type == TaskType.REMOVE) {
                        todos.removeTask(operation.task);
                    }
                    out.println(todos.getAllTasks());
                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }
}

