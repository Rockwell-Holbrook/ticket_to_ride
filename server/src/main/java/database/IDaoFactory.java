package database;

public interface IDaoFactory {
    public IGameDao createGameDao();
    public IUserDao createUserDao();
}
