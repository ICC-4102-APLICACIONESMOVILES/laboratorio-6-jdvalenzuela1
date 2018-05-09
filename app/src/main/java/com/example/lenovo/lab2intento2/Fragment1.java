package com.example.lenovo.lab2intento2;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.lenovo.lab2intento2.Database.FormDatabase;
import com.example.lenovo.lab2intento2.Modelos.Forms;

import java.lang.reflect.Array;
// Agregar Formulario

public class Fragment1 extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    Button aceptar;

    EditText nombre;
    DatePicker fecha;
    Spinner opciones;
    EditText comentario;

    private static final String DATABASE_NAME = "forms_db";
    private FormDatabase formDatabase;

    private OnFragmentInteractionListener mListener;

    public static Fragment1 newInstance(String param1, String param2) {
        Fragment1 fragment = new Fragment1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        aceptar = (Button) getActivity().findViewById(R.id.aceptar);

        nombre = (EditText) getActivity().findViewById(R.id.nombre);
        fecha = (DatePicker) getActivity().findViewById(R.id.fecha);
        opciones = (Spinner) getActivity().findViewById(R.id.sp01);
        comentario = (EditText) getActivity().findViewById(R.id.comentario);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.opciones, android.R.layout.simple_spinner_item);
        opciones.setAdapter(adapter);

        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nombre.getText().toString().equals("") || Integer.toString(fecha.getYear()).equals("") || opciones.getSelectedItem().toString().equals("") || comentario.getText().toString().equals("")){
                    Toast.makeText(getActivity(), "Faltan datos por completar", Toast.LENGTH_LONG).show();
                }
                else{

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            String nombreString = nombre.getText().toString();
                            String fechaString = Integer.toString(fecha.getYear()) + "/"+Integer.toString(fecha.getMonth()) + "/"+Integer.toString(fecha.getDayOfMonth());
                            String categoriaString = opciones.getSelectedItem().toString();
                            String comentarioString = comentario.getText().toString();

                            formDatabase = Room.databaseBuilder(getActivity(),FormDatabase.class, DATABASE_NAME).build();
                            Forms form =new Forms();
                            form.setName(nombreString);
                            form.setDate(fechaString);
                            form.setCategory(categoriaString);
                            form.setComment(comentarioString);
                            formDatabase.daoForms().insertOnlySingleForm(form);
                        }
                    }) .start();

                    Toast.makeText(getActivity(), "Formulario Guardado con exito", Toast.LENGTH_LONG).show();
                }
            }
        });



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment1, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
