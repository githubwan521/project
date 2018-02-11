package IDao;


import Model.Studio;

import java.util.List;

public interface StudioImpl {
    // 增
    public boolean insert(Studio studio);

    // 删
    public boolean delete(int studioId);

    // 改
    public boolean update(Studio studio);

    // 查所有用户(一般用于和界面交互)
    public List<Studio> findStudioAll();

    // 根据用户名查(一般用于和界面交互)
    public List<Studio> findStudioByName(String studioName);

    // 根据用户id查(一般用于数据内部关联操作)
    public Studio findStudioById(int studioId);

    public int getAllCount();

    public int getAllPageCount();

    public int getCurrentPage();

    public List<Studio> findStudioByPage(int currentPage, String studioName);

}
