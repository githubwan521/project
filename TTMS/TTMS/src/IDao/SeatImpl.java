package IDao;


import Model.Seat;

import java.util.ArrayList;

public interface SeatImpl
{
    // 增
    public boolean insert(Seat seat);

    // 删
    public boolean delete(int seatId);

    // 改
    public boolean update(Seat seat);

    public ArrayList<Seat> findSeatAll();

    public Seat findSeatById(int seatNo);

    public ArrayList<Seat> selectByStudio(int studioid);

    public Seat selectByMany(int studioid, int seat_row, int seat_column);

    // public Seat findSeatById(int seatId);
}
