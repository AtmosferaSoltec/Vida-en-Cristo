import { NestFactory } from '@nestjs/core';
import { AppModule } from './app.module';
import { Logger, ValidationPipe } from '@nestjs/common';
import { httpsCertificate } from './config/https-certificate';

async function bootstrap() {
  const port = process.env.PORT || 3000;
  const app = await NestFactory.create(AppModule, {
    httpsOptions: httpsCertificate(),
  });
  app.setGlobalPrefix('api');
  app.useGlobalPipes(new ValidationPipe());
  app.enableCors();
  await app.listen(port);
  const logger = new Logger('bootstrap');
  logger.log(`Applicacion corriendo en el puerto: ${port}`);
}
bootstrap();
