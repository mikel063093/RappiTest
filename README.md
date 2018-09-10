# Rappi test
Basado en la arquitectura MVP, modelo vista presentador, para un desacople de dependencias.

## Capas

| Capa | Descripción |
| ------ | ------ |
| di | Contiene las clases de inyeccion de dependencias|
| model | permite el acceso y gestion a los datos ya sean locales(Realm) o externos(Api rest)
| mvp | contiene las clases de logica del negocio, permitiendo separar la vista de la logica |
| ui | contiene las clases de vista
| event | contiene los mensajes  a transmitir entre los diferentes servicios de la app
| bus | permite enviar los eventos a diferentes componentes de la app





## Responsabilidad única

El objetivo de este principio es de mantener un bajo acoplamiento, es decir, reducir al máximo posible el grado de la relación de las clases o módulos con el resto, para favorecer crear código más fácilmente mantenible, extensible y testeable. El objetivo principal no es otro que disminuir el acoplamiento.

## Código limpio

El código limpio es aquel que ha sido escrito de forma coherente, sus nombres de variables y métodos tienen sentido, en pocas palabras es aquel que a simple vista no tiene nada evidente para mejorar.


# Librerias usadas

- [Dagger2](https://github.com/google/dagger)
- [RxJava and RxAndroid](https://github.com/ReactiveX/RxJava)
- [Retrofit](https://github.com/square/retrofit)
- [GSON](https://github.com/google/gson)
- [OkHttp](https://github.com/square/okhttp)
- [ButterKnife](https://github.com/JakeWharton/butterknife)
- [RetroLambda](https://github.com/evant/gradle-retrolambda)
- [Glide](https://github.com/bumptech/glide)
- [Timber](https://github.com/JakeWharton/timber)


