Atualizações realizadas:

I - Entidades: User, Track, Like, conceito de Follow
II - Relacionamentos: @ManyToOne, @OneToMany
III - UUID para Track
IV - Constraints de banco
V - Lombok funcionando 

(80% da modelagem está pronta[camada de dados])

Necessidades para o BackEnd:

I - Repositories

UserRepository
TrackRepository
LikeRepository
FollowRepository
CommentRepository (se tiver)

II - Services (regra de negócio) // sem service o backend é só estrutural

Criar usuário
Login
Upload de música
Curtir música
Seguir usuário
Buscar músicas

III - Controllers (API REST) 
Criação dos endpoints

POST   /users
POST   /auth/login
POST   /tracks
GET    /tracks
POST   /tracks/{id}/like
POST   /users/{id}/follow

IV - Autenticação:

Spring Security
JWT
BCrypt


Os requisitos de FrontEnd e a integração AWS serão discutidas assim que o BackEnd do projeto permanecerem na versão 1.0.0
