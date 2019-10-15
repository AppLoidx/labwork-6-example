package commands.concrete;

import commands.CollectionAction;
import commands.Command;
import ds.jdbc.UserDB;
import entities.User;
import network.mail.GmailSender;
import util.Password;

import java.sql.SQLException;

/**
 * @author Arthur Kupriyanov
 */
public class Register extends Command {
    @Override
    public String getName() {
        return "register";
    }

    @Override
    public CollectionAction getAction(String context) {
        String[] ctx = context.split(" ");
        if (ctx.length != 2){
            return (col) -> "Неверный формат команды";
        }

        String username = ctx[0];
        String email = ctx[1];
        try {
            UserDB db = new UserDB();
            if (db.checkUserExist(username)){
                return (col) -> "Пользователь с таким именем уже существует";
            }
            String password = Password.generatePassword(username);
            db.saveUser(new User(username, Password.hash(password)));
            GmailSender.send(email, "Your password : " + password);
            return (col) -> "Вы успешно зарегистрированы! Ваш пароль отправлен на почту: " + email;
        } catch (SQLException e) {
            e.printStackTrace();
            return (col) -> "Не удалось подключиться к БД или произошла ошибка: " + e.getMessage();
        }
    }
}
