package repository;

import entity.Todolist;

public class TodoListRepositoryImpl implements TodoListRepository {

    public Todolist[] data = new Todolist[10];

    @Override
    public Todolist[] getAll() {
        return data;
    }

    public boolean isFull() {
        //cek task penuh atau tidak
        boolean isFull = true;
        for (int index = 0; index < data.length; index++) {
            if (data[index] == null) {
                //task masih ada yang kosong
                isFull = false;
                break;
            }
        }
        return isFull;
    }

    public void resizeIfFull() {
        //jika penuh, akan resize ukuran array 2x lipat
        if (isFull()) {
            Todolist[] temp = data;
            data = new Todolist[data.length * 2];

            for (int index = 0; index < temp.length; index++) {
                data[index] = temp[index];
            }
        }
    }

    @Override
    public void add(Todolist todolist) {
        resizeIfFull();

        // menambahkan data ke posisi array null
        for (int index = 0; index < data.length; index++) {
            if (data[index] == null) {
                data[index] = todolist;
                break;
            }
        }
    }

    @Override
    public boolean remove(Integer number) {

        if ((number - 1) >= data.length) {
            return false;
        } else if (data[number - 1] == null) {
            return false;
        } else {
            for (int index = (number - 1); index < data.length; index++) {
                if (index == (data.length - 1)) {
                    data[index] = null;
                } else {
                    data[index] = data[index + 1];
                }
            }
            return true;
        }

    }
}
