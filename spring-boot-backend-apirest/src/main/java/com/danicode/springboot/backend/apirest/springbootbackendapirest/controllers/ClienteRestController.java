package com.danicode.springboot.backend.apirest.springbootbackendapirest.controllers;

import com.danicode.springboot.backend.apirest.springbootbackendapirest.models.entity.Cliente;
import com.danicode.springboot.backend.apirest.springbootbackendapirest.models.services.IClienteService;
import com.danicode.springboot.backend.apirest.springbootbackendapirest.models.services.IUploadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/clientes")
public class ClienteRestController {
    @Autowired
    private IClienteService clienteService;

    @Autowired
    private IUploadService uploadService;

    private final Logger log = LoggerFactory.getLogger(ClienteRestController.class);

    @GetMapping({"/", "", "/all"})
    public List<Cliente> index() {
        return this.clienteService.findAll();
    }

    @GetMapping({"/page/{page}", "", "/all/page/{page}"})
    public Page<Cliente> index(@PathVariable Integer page) {
        Pageable pageable = PageRequest.of(page, 4);
        return this.clienteService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {
        Cliente cliente = null;
        Map<String, Object> response = new HashMap<>();
        try {
            cliente = this.clienteService.findById(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error de servidor (show): *** "
                    .concat(e.getMessage())
                    .concat(" *** :")
                    .concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (cliente == null) {
            response.put("mensaje", "El cliente con ID: ".concat(id.toString()).concat(" no existe"));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
    }

    @PostMapping({"/", ""})
    public ResponseEntity<?> store(@Valid @RequestBody Cliente cliente, BindingResult result) {
        Cliente clienteNuevo = null;
        Map<String, Object> response = new HashMap<>();
        if (result.hasErrors()) {
            List<String> errores = result.getFieldErrors()
                    .stream()
                    .map(error -> "El campo '" + error.getField() + "' " + error.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errores", errores);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            clienteNuevo = this.clienteService.save(cliente);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error de servidor (store): *** "
                    .concat(e.getMessage())
                    .concat(" *** :")
                    .concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "Cliente creado satisfactoriamente");
        response.put("cliente", clienteNuevo);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Cliente cliente, BindingResult result, @PathVariable Long id) {
        Cliente clienteActual = this.clienteService.findById(id);
        Cliente clienteActualizado = null;
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            List<String> errores = result.getFieldErrors()
                    .stream()
                    .map(error -> "El campo '" + error.getField() + "' " + error.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errores", errores);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        if (clienteActual == null) {
            response.put("mensaje", "El cliente con ID: ".concat(id.toString()).concat(" no existe"));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        try {
            clienteActual.setApellido(cliente.getApellido());
            clienteActual.setNombre(cliente.getNombre());
            clienteActual.setEmail(cliente.getEmail());

            clienteActualizado = this.clienteService.save(clienteActual);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error de servidor (update): *** "
                    .concat(e.getMessage())
                    .concat(" *** :")
                    .concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "Cliente actualizado satisfactoriamente");
        response.put("cliente", clienteActualizado);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            // Eliminando la foto
            Cliente cliente = this.clienteService.findById(id);
            String fotoAnterior = cliente.getFoto();
            this.uploadService.eliminar(fotoAnterior);
            this.clienteService.delete(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error de servidor (delete): *** "
                    .concat(e.getMessage())
                    .concat(" *** :")
                    .concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "Cliente eliminado satisfactoriamente");

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        Cliente cliente = this.clienteService.findById(id);
        if (!archivo.isEmpty()) {
            String nombreArchivo = "";
            try {
                nombreArchivo = this.uploadService.guardarImage(archivo);
            } catch (IOException e) {
                response.put("mensaje", "Error de servidor (upload): *** "
                        .concat(e.getMessage())
                        .concat(" *** :")
                        .concat(e.getCause().getMessage()));
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            // Validar si cliente tiene una foto asociada y eliminarla para subir a nueva foto
            String fotoAnterior = cliente.getFoto();
            this.uploadService.eliminar(fotoAnterior);
            cliente.setFoto(nombreArchivo);
            this.clienteService.save(cliente);

            response.put("cliente", cliente);
            response.put("mensaje", "La imagen " + nombreArchivo + " fue subida correctamente");

        }
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    /* metodo para mostrar la foto en el navegador: Se descarga la imagen cuando se valla a la ruta
     * http://localhost:8080/api/uploads/img/as12nkcaskcn2ne12.jpg */
    @GetMapping("/uploads/img/{nombreFoto:.+}")
    public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto) {
        Resource resource = null;
        try {
            resource = this.uploadService.cargar(nombreFoto);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"");

        return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
    }
}
